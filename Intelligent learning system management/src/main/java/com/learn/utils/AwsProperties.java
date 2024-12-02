package com.learn.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aws.s3")
public class AwsProperties {
    private String endpoint;
    private String bucketName;
    private String region;
}
