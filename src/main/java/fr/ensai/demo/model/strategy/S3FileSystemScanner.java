package fr.ensai.demo.model.strategy;

import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.model.filesystem.FolderComponent;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.ArrayList;
import java.util.List;

import fr.ensai.demo.model.scan.Scan;
import fr.ensai.demo.model.visitor.FileSystemVisitor;

import java.io.File; 
import java.util.Date;
import java.text.*;

public class S3FileSystemScanner implements InterfaceFileSystemScannerStrategy {

    @Override
    public FolderComponent importFileSystem(String bucketName, int currentDepth) {
        S3Client s3Client = S3Client.builder().region(Region.US_EAST_1).build();
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder().bucket(bucketName).build();
        ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest);
        List<S3Object> s3Objects = listObjectsResponse.contents();

        FolderComponent rootFolder = new FolderComponent(bucketName, "/", currentDepth);

        for (S3Object s3Object : s3Objects) {
            String key = s3Object.key();

            String parentFolderName = key.substring(0, key.lastIndexOf("/"));
            String fileName = key.substring(key.lastIndexOf("/") + 1);

            rootFolder.addComponent(new FileLeaf(fileName, parentFolderName, "", "", 0, currentDepth));
        }

        return rootFolder;
    }

    @Override
    public Scan scanFileSystem(FolderComponent folder, String filenameFilter, String extensionFilter, int maxFiles, int maxDepth) {
        
        FileSystemVisitor visitor = new FileSystemVisitor(filenameFilter, extensionFilter, maxDepth, maxFiles);
        folder.accept(visitor);
        List<FileLeaf> visitedFiles = visitor.getVisitedFiles();
        int size = 0;
        for (FileLeaf file : visitedFiles) {
            size+=(file.getSize());
        };
        Scan scan = new Scan(0, "local file system", "11/02", filenameFilter, extensionFilter, folder.getName(), 0, size, maxFiles, maxDepth, visitedFiles);
        return scan;
    }
}