package com.demo.zero.cloud.aliyun.sms;


import com.demo.zero.cloud.aliyun.sms.sink.AdminLoginLogSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author 哈哈呵呵君
 * @date: 2020/2/1 04:35
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding({AdminLoginLogSink.class})
public class AliyunSmsApplication {
    public static void main(String[] args){
        SpringApplication.run(AliyunSmsApplication.class,args);
    }
}
