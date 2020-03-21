package com.demo.zero.service.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录参数
 */
@Data
public class LoginParam implements Serializable {
    private String username;
    private String password;
}
