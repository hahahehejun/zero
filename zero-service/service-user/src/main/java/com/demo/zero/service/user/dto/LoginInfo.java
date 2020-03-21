package com.demo.zero.service.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录信息
 */
@Data
public class LoginInfo implements Serializable {
    private String name;
    private String avatar;
    private String nickName;
}
