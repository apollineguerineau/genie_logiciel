package fr.ensai.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.model.filesystem.FolderComponent;
import fr.ensai.demo.model.strategy.LocalFileSystemScanner;
import fr.ensai.demo.model.visitor.FileSystemVisitor;

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

        // Generating the response
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("<h1>List of files:</h1>");
        for (FileLeaf file : visitedFiles) {
            responseBuilder.append("<p>Name: ").append(file.getName()).append("</p>");
            responseBuilder.append("<p>File Extension: ").append(file.getFileExtension()).append("</p>");
            responseBuilder.append("<p>Parent Folder Name: ").append(file.getParentFolderName()).append("</p>");
            responseBuilder.append("<p>Modification Date: ").append(file.getModificationDate()).append("</p>");
            responseBuilder.append("<p>Size: ").append(file.getSize()).append("</p>");
        }

        return responseBuilder.toString();
    }
}



