// package fr.ensai.demo.model.strategy;

// import fr.ensai.demo.model.filesystem.FileLeaf;
// import fr.ensai.demo.model.filesystem.FolderComponent;

// import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
// import software.amazon.awssdk.regions.Region;
// import software.amazon.awssdk.services.s3.S3Client;
// import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
// import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
// import software.amazon.awssdk.services.s3.model.S3Object;

// import java.util.ArrayList;
// import java.util.List;

// import fr.ensai.demo.model.scan.Scan;
// import java.io.File; 
// import java.util.Date;
// import java.text.*;

// public class S3FileSystemScanner implements InterfaceFileSystemScannerStrategy {
//     private final S3Client s3Client;

//     public S3FileSystemScanner() {
//         this.s3Client = S3Client.builder()
//                                  .credentialsProvider(DefaultCredentialsProvider.create())
//                                  .region(Region.US_EAST_1) // Remplacez par la région appropriée
//                                  .build();
//     }

//     @Override
//     public FolderComponent importFileSystem(String path, int currentDepth) {
//         return null;
//     }

//     @Override
//     public Scan scanFileSystem(FolderComponent folder, String filenameFilter, String extensionFilter, int maxFiles, int maxDepth) {
//         String bucket_name = "bucketprojectapolline";
//         List<FileLeaf> fileLeaves = new ArrayList<>();
//         ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucket_name).build();
//         ListObjectsV2Response response = s3Client.listObjectsV2(request);
//         List<S3Object> objects = response.contents();

//         for (S3Object object : objects) {
//             String key = object.key();
//             String fileName = key.substring(key.lastIndexOf('/') + 1);
//             String fileExtension = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf('.') + 1) : "";
//             String parentFolderName = key.substring(0, key.lastIndexOf('/'));
//             String lastModified = object.lastModified().toString();
//             long size = object.size();

//             FileLeaf fileLeaf = new FileLeaf(fileName, parentFolderName, fileExtension, lastModified, size, 0);
//             fileLeaves.add(fileLeaf);
//         }

//         return new Scan(1,"S3 Scan", "date", filenameFilter, extensionFilter, "root", 0, fileLeaves.size(), maxFiles, maxDepth, fileLeaves);
//     }
// }