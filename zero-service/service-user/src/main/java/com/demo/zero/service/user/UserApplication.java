package com.demo.zero.service.user;

import com.demo.zero.service.user.source.MessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EntityScan(basePackages = "com.demo.zero.user.domain")
@SpringBootApplication
@EnableSwagger2
@EnableBinding({MessageSource.class})
public class UserApplication {
    public static void main(String[] args){
        SpringApplication.run(UserApplication.class,args);
    }
}
