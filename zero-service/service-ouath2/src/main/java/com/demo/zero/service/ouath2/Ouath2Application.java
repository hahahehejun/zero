package com.demo.zero.service.ouath2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Ouath2Application {
    public static void main(String[] args){
        SpringApplication.run(Ouath2Application.class,args);
    }
}
