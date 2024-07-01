//package com.store.ezbuy.service;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.amazonaws.services.s3.model.S3Object;
//import com.amazonaws.services.s3.model.S3ObjectInputStream;
//import com.amazonaws.util.IOUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Service
//public class AWSFileServiceImpl implements ImageService {
//
//
//    @Value("${cloud.s3.bucket.name}")
//    private String bucketName;
//
//    private final AmazonS3 amazonS3;
//
//    public AWSFileServiceImpl(AmazonS3 amazonS3) {
//        this.amazonS3 = amazonS3;
//    }
//
//    @Override
//    public String uploadFile(MultipartFile file) throws IOException {
//
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentType(file.getContentType());
//        objectMetadata.setContentLength(file.getSize());
//
//        amazonS3.putObject(new PutObjectRequest(bucketName, file.getOriginalFilename(), file.getInputStream(), objectMetadata));
//
//        return amazonS3.getUrl(bucketName,file.getOriginalFilename()).toString();
//    }
//
//    public byte[] getFileContent(String imageName) throws IOException {
//        S3Object s3Object = amazonS3.getObject(bucketName, imageName);
//        S3ObjectInputStream inputStream = s3Object.getObjectContent();
//        try {
//            return IOUtils.toByteArray(inputStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            inputStream.close();
//            s3Object.close();
//        }
//    }
//
//    @Override
//    public String getContentType(String imageName) {
//        return amazonS3.getObjectMetadata(bucketName, imageName).getContentType();
//    }
//
//    @Override
//    public boolean deleteFile(String fileName) {
//        return false;
//    }
//}
