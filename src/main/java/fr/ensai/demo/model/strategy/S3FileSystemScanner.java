package fr.ensai.demo.model.strategy;

import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.model.filesystem.FolderComponent;
import fr.ensai.demo.model.filesystem.InterfaceFileSystemComponent;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

import fr.ensai.demo.model.scan.Scan;
import fr.ensai.demo.model.visitor.FileSystemVisitor;



public class S3FileSystemScanner implements InterfaceFileSystemScannerStrategy {

    @Override
    public FolderComponent importFileSystem(String bucketName, int currentDepth) {
    
    S3Client s3Client = S3Client.builder().region(Region.US_EAST_1).build();
    ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder().bucket(bucketName).build();
    ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest);
    List<S3Object> s3Objects = listObjectsResponse.contents();

    FolderComponent rootFolder = new FolderComponent(bucketName, bucketName, currentDepth);

    for (S3Object s3Object : s3Objects) {

        try {
            String absolutePath = bucketName + "/" + s3Object.key();
            String[] decomposedPath = absolutePath.split("/");
            String fileName = decomposedPath[decomposedPath.length - 1];
            String parentFolderName;
            if(absolutePath.lastIndexOf("/") == -1){
                parentFolderName = bucketName;
            } else {
                parentFolderName = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
            }

            FolderComponent currentFolder = rootFolder;

            for(int i = 1; i < decomposedPath.length - 1; i++){

                boolean found = false;
                String folderPath = absolutePath.substring(0,StringUtils.ordinalIndexOf(absolutePath, "/", i + 1));

                for(InterfaceFileSystemComponent component: currentFolder.getComponents()){
                    if ((component instanceof FolderComponent)){
                        FolderComponent componentCasted = (FolderComponent)component;
                        //if folder exists, great, just use it
                        if(componentCasted.getAbsolutePath() == folderPath){
                            currentFolder = componentCasted;
                            found = true;
                            break;
                        }
                    }
                }
                // if not found, just create it!
                if(!found){
                    FolderComponent newFolder = new FolderComponent(decomposedPath[i], absolutePath, i);
                    currentFolder.addComponent(newFolder);
                    currentFolder = newFolder;
                }
            }

            // retrieve object metadata
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(s3Object.key())
                        .build();
            ResponseBytes<GetObjectResponse> responseBytes = s3Client.getObjectAsBytes(getObjectRequest);
            GetObjectResponse getObjectResponse = responseBytes.response();
            
            // Extraire les informations pertinentes des métadonnées
            String extension = getFileExtension(fileName);
            Instant modificationDateInstant = getObjectResponse.lastModified();
            ZonedDateTime modificationDateTime = modificationDateInstant.atZone(ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String formattedDate = formatter.format(modificationDateTime);
            long size = getObjectResponse.contentLength();
            
            // Créer un objet FileLeaf avec les informations extraites
            System.out.print(fileName);
            int l = (int)(decomposedPath.length - 1);
            FileLeaf fileLeaf = new FileLeaf(fileName, parentFolderName, extension, formattedDate, size, l);
            // Ajouter le FileLeaf au du système de fichiers
            currentFolder.addComponent(fileLeaf);

            // Récupérer les métadonnées de l'objet S3
        
        } catch (NoSuchKeyException e) {
            // Gérer l'erreur si la clé n'existe pas dans le bucket
            System.err.println("Object not found: " + e.getMessage());
        } catch (Exception e) {
            // Gérer d'autres erreurs potentielles
            e.printStackTrace();
        }
    }

    return rootFolder;
}

    // Méthode pour extraire l'extension d'un nom de fichier
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    @Override
    public Scan scanFileSystem(FolderComponent folder, String filenameFilter, String extensionFilter, int maxFiles, int maxDepth) {
        
        FileSystemVisitor visitor = new FileSystemVisitor(filenameFilter, extensionFilter, maxDepth, maxFiles);
        float startTime = (float) System.currentTimeMillis();
        folder.accept(visitor);
        float endTime = (float) System.currentTimeMillis(); // Mesure du temps de fin
        float executionTime = endTime - startTime;
        List<FileLeaf> visitedFiles = visitor.getVisitedFiles();
        int size = 0;
        for (FileLeaf file : visitedFiles) {
            size+=(file.getSize());
        };
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = currentDate.format(formatter);
        Scan scan = new Scan("S3 file system", formattedDate, filenameFilter, extensionFilter, folder.getAbsolutePath(), executionTime, size, maxFiles, maxDepth, visitedFiles);
        return scan;
    }
}