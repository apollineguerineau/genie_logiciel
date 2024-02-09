package fr.ensai.demo;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
// import com.amazonaws.regions.Regions;
// import com.amazonaws.services.s3.AmazonS3;
// import com.amazonaws.services.s3.AmazonS3ClientBuilder;
// import com.amazonaws.services.s3.model.ListObjectsV2Result;
// import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3FileScanner {
    public static void main(String[] args) {

        String bucketName = "bucketprojectapolline";

        System.out.format("Objects in S3 bucket %s:\n", bucketName);
        final S3Client s3 = S3Client.builder().region(Region.US_EAST_1).build();
        ListObjectsV2Response response = s3.listObjectsV2(ListObjectsV2Request.builder().bucket(bucketName).build());
        for (S3Object object : response.contents()) {
            String fileName = object.key();
            String lastModified = object.lastModified().toString();
            long size = object.size();
            String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
            String directory = fileName.contains("/") ? fileName.substring(0, fileName.lastIndexOf('/')) : "";

            System.out.println("File Name: " + fileName);
            System.out.println("Last Modified: " + lastModified);
            System.out.println("Size: " + size + " bytes");
            System.out.println("File Type: " + fileType);
            System.out.println("Directory: " + directory);
            System.out.println("-----------------------");
        }
    }
}