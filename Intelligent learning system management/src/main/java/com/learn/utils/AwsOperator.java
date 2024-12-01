package com.learn.utils;

//import com.learn.awssdk.*;
//import com.aliyun.oss.common.auth.CredentialsProviderFactory;
//import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
//import com.aliyun.oss.common.comm.SignVersion;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.client.config.SdkAdvancedClientOption;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Component
public class AwsOperator {

    private String endpoint = "https://s3.eu-central-1.amazonaws.com";
    private String bucketName = "learncontroller";
    private String region = "eu-central-1";

    public static S3Client createS3Client(String endpoint, String regionStr, AwsCredentialsProvider credentialsProvider) {
        // Create a custom client configuration
        Region region = Region.of(regionStr);
        ClientOverrideConfiguration clientConfig = ClientOverrideConfiguration.builder()
                .build();

        // Build and return the S3 client
        return S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(credentialsProvider)
                .region(region)
                .overrideConfiguration(clientConfig)
                .build();
    }

    public String upload(byte[] content, String originalFilename) throws Exception {
        EnvironmentVariableCredentialsProvider credentialsProvider = EnvironmentVariableCredentialsProvider.create();

        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        S3Client s3Client = createS3Client(endpoint, region, credentialsProvider);
        try {
//            s3Client.putObject(bucketName, objectName, new ByteArrayInputStream(content));

            // Create PutObjectRequest
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName) // Specify bucket name
                    .key(objectName) // Specify object name (key)
                    .build();
            // Upload the content
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(content));

        } finally {
            s3Client.close();
        }
        return "https://" + bucketName + ".s3.amazonaws.com/" + objectName;
//        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
    }
}

