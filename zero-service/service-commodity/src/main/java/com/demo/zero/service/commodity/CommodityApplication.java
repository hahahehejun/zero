package com.demo.zero.service.commodity;

import com.demo.zero.service.commodity.source.MessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/6 21:24
 */
@EnableDiscoveryClient
@EntityScan(basePackages = "com.demo.zero.commodity.domain")
@SpringBootApplication
@EnableSwagger2
@EnableBinding({MessageSource.class})
public class CommodityApplication {
    public static void main(String[] args){
        SpringApplication.run(CommodityApplication.class,args);
    }
}
