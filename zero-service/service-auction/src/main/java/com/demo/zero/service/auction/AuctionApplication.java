package com.demo.zero.service.auction;

import com.demo.zero.service.auction.source.MessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/9 04:40
 */
@EnableDiscoveryClient
@EntityScan(basePackages = "com.demo.zero.auction.domain")
@SpringBootApplication
@EnableSwagger2
@EnableBinding({MessageSource.class})
public class AuctionApplication {
    public static void main(String[] args){
        SpringApplication.run(AuctionApplication.class,args);
    }
}
