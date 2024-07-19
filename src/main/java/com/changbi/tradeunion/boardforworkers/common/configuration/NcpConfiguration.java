package com.changbi.tradeunion.boardforworkers.common.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NcpConfiguration {

    public static String accessKey;
    public static String secretKey;
    public static String endPoint;
    public static String regionName;

    @Value("${cloud.ncp.credentials.access-key}")
    public void setAccessKey(String accessKey) {
        NcpConfiguration.accessKey = accessKey;
    }

    @Value("${cloud.ncp.credentials.secret-key}")
    public void setSecretKey(String secretKey) {
        NcpConfiguration.secretKey = secretKey;
    }

    @Value("${cloud.ncp.end-point}")
    public void setEndPoint(String endPoint) {
        NcpConfiguration.endPoint = endPoint;
    }

    @Value("${cloud.ncp.region-name}")
    public void setRegionName(String regionName) {
        NcpConfiguration.regionName = regionName;
    }

    @Bean
    public AmazonS3 amazonS3(){
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

}
