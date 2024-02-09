package fr.ensai.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.model.filesystem.FolderComponent;
import fr.ensai.demo.model.strategy.LocalFileSystemScanner;
import fr.ensai.demo.model.visitor.FileSystemVisitor;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

@RestController
public class HelloWorldController {
    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }

    @GetMapping("/localfilesystem")
    public String getLocalFileSystemInfo() {
        // Creating the root directory
        LocalFileSystemScanner localFileSystemScanner = new LocalFileSystemScanner();
        FolderComponent rootDirectory = localFileSystemScanner.importFileSystem("/home/ensai/Documents/3A/GENIE_LOGICIEL/all/dirtest", 0);

        // Visiting the directory to get the list of files
        FileSystemVisitor visitor = new FileSystemVisitor("1", ".txt", 3, 100);
        rootDirectory.accept(visitor);

        // Obtaining the list of visited files
        List<FileLeaf> visitedFiles = visitor.getVisitedFiles();

        // Generating the HTML table
        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("<table border=\"1\">");
        tableBuilder.append("<tr><th>Name</th><th>File Extension</th><th>Parent Folder Name</th><th>Modification Date</th><th>Size</th></tr>");
        for (FileLeaf file : visitedFiles) {
            tableBuilder.append("<tr>");
            tableBuilder.append("<td>").append(file.getName()).append("</td>");
            tableBuilder.append("<td>").append(file.getFileExtension()).append("</td>");
            tableBuilder.append("<td>").append(file.getParentFolderName()).append("</td>");
            tableBuilder.append("<td>").append(file.getModificationDate()).append("</td>");
            tableBuilder.append("<td>").append(file.getSize()).append("</td>");
            tableBuilder.append("</tr>");
        }
        tableBuilder.append("</table>");

        return tableBuilder.toString();
    }

    @GetMapping("/S3")
    public String getS3FileSystem() {
        String bucketName = "bucketprojectapolline";

        final S3Client s3 = S3Client.builder().region(Region.US_EAST_1).build();
        ListObjectsV2Response response = s3.listObjectsV2(ListObjectsV2Request.builder().bucket(bucketName).build());

        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("<table border=\"1\">");
        tableBuilder.append("<tr><th>Name</th><th>File Extension</th><th>Parent Folder Name</th><th>Modification Date</th><th>Size</th></tr>");

        for (S3Object object : response.contents()) {
            String fileName = object.key();
            String lastModified = object.lastModified().toString();
            long size = object.size();
            String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
            String directory = fileName.contains("/") ? fileName.substring(0, fileName.lastIndexOf('/')) : "";

            tableBuilder.append("<tr>");
            tableBuilder.append("<td>").append(fileName).append("</td>");
            tableBuilder.append("<td>").append(fileType).append("</td>");
            tableBuilder.append("<td>").append(directory).append("</td>");
            tableBuilder.append("<td>").append(lastModified).append("</td>");
            tableBuilder.append("<td>").append(size).append("</td>");
            tableBuilder.append("</tr>");
        }
        tableBuilder.append("</table>");

        return tableBuilder.toString();

    }
}



