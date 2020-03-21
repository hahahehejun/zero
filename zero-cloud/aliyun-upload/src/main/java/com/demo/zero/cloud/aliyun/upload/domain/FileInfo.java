package com.demo.zero.cloud.aliyun.upload.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo implements Serializable {
    private String path;
}
