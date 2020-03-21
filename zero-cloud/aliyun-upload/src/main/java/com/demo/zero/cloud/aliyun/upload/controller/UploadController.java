package com.demo.zero.cloud.aliyun.upload.controller;


import com.demo.zero.cloud.aliyun.upload.domain.FileInfo;
import com.demo.zero.cloud.aliyun.upload.service.UploadService;
import com.demo.zero.commons.dto.BaseResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**@details: 文件上传
 * @author 哈哈呵呵君
 */
@CrossOrigin
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private UploadService uploadService;

    /**
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping(value = "")
    public BaseResult test(MultipartFile multipartFile) throws IOException {
        FileInfo fileInfo = new FileInfo(uploadService.upload(multipartFile));
        return BaseResult.success("文件上传成功",fileInfo);
    }
}
