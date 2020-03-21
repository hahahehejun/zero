package com.demo.zero.cloud.aliyun.upload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/4 01:53
 */
public interface UploadService {
    String upload(MultipartFile multipartFile) throws IOException;
}
