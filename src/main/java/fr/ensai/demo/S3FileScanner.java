package fr.ensai.demo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.List;

public class S3FileScanner {
    public static void main(String[] args) {

        String bucket_name = "bucketprojectapolline";

        System.out.format("Objects in S3 bucket %s:\n", bucket_name);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        ListObjectsV2Result result = s3.listObjectsV2(bucket_name);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        for (S3ObjectSummary os : objects) {
            String fileName = os.getKey();
            String lastModified = os.getLastModified().toString();
            long size = os.getSize();
            String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
            String directory = fileName.substring(0, fileName.lastIndexOf('/'));

            System.out.println("File Name: " + fileName);
            System.out.println("Last Modified: " + lastModified);
            System.out.println("Size: " + size + " bytes");
            System.out.println("File Type: " + fileType);
            System.out.println("Directory: " + directory);
            System.out.println("-----------------------");
        }
    }
}