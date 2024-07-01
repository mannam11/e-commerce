//package com.store.ezbuy.config;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AWSConfig {
//
//    @Value("${cloud.aws.accessKey}")
//    private String accessKey;
//
//    @Value("${cloud.aws.secretKey}")
//    private String secretKey;
//
//
//    @Bean
//    public AmazonS3 amazonS3Client() {
//        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
//        return AmazonS3ClientBuilder.standard()
//                .withRegion(Regions.AP_SOUTH_1)
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .build();
//    }
//
//}
