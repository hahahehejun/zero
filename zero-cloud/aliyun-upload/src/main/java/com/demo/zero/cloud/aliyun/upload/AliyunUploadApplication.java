package com.demo.zero.cloud.aliyun.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/4 01:41
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AliyunUploadApplication {
    public static void main(String[] args){
        SpringApplication.run(AliyunUploadApplication.class,args);
    }
}
