package com.demo.zero.service.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/6 01:56
 */
@Data
public class PasswordParam implements Serializable {
    private String username;
    private String oldPassword;
    private String newPassword;
}
