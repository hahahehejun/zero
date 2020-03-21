package com.demo.zero.cloud.aliyun.upload.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.demo.zero.cloud.aliyun.upload.service.UploadService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@Service(version = "1.0.0")
public class UploadServiceImpl implements UploadService {

    @Value("${base.key.oss.endpoint}")
    private String endpoint;
    @Value("${base.key.oss.accessKeyId}")
    private String accessKeyId ;
    @Value("${base.key.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${base.key.oss.bucketName}")
    private String bucketName;

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {

        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newName = UUID.randomUUID() + "." + suffix;

        // <yourObjectName>从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "zero/"+newName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(multipartFile.getBytes()));
        // 关闭OSSClient。
        ossClient.shutdown();
        return "http://" + bucketName + "." + endpoint + "/" + objectName;
    }



}
