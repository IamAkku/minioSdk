package com.api.miniosdk.services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import io.minio.BucketExistsArgs;
import io.minio.DownloadObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;

@Service
public class MinIOService {

    final static String endPoint = "http://127.0.0.1:9000";
    final static String accessKey = "test";
    final static String secretKey = "test1234";
    final static String bucketName = "bucket";
    final static String localFileFolder = "C:\\Users\\DELL\\Desktop\\minioApi\\";

    public void WriteToMinIO(String fileName)
            throws InvalidKeyException, IllegalArgumentException, NoSuchAlgorithmException, IOException {

        System.out.println("Bucket start!  ");

        try {
            MinioClient minioClient = MinioClient.builder().endpoint(endPoint)
                    .credentials(accessKey, secretKey).build();

            System.out.println("Bucket running!  ");

            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

            if (!bucketExists) {
                System.out.println("code running  ");
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
                    else{

                System.out.println("running");


            }
            String fileToUpload = localFileFolder + fileName;
            UploadObjectArgs args = UploadObjectArgs.builder().bucket(bucketName).object(fileName)
                    .filename(fileToUpload).build();
            //minioClient.uploadObject(args);

            minioClient.listBuckets().get(0).name();
            System.out.println(fileToUpload + " successfully uploaded to:");
            System.out.println("   container: " + bucketName);
            System.out.println("   blob: " + fileName);
            System.out.println();
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    public void ReadFromMinIO(String fileName)
            throws InvalidKeyException, IllegalArgumentException, NoSuchAlgorithmException, IOException {
        try {
            MinioClient minioClient = MinioClient.builder().endpoint(endPoint)
                    .credentials(accessKey, secretKey).build();
            String downloadedFile = localFileFolder + "D_" + fileName;
            //DownloadObjectArgs args = DownloadObjectArgs.builder().bucket(bucketName).object(fileName)
                    //.filename(downloadedFile).build();
            //minioClient.downloadObject(args);
            System.out.println(minioClient.listBuckets().get(0).name());
            System.out.println("Downloaded file to ");
            System.out.println(" " + downloadedFile);
            System.out.println();
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
    
}
