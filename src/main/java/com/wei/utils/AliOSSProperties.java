package com.wei.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {
//    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
//    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
}
