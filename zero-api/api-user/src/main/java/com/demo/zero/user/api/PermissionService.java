package com.demo.zero.user.api;

import com.demo.zero.user.domain.Permission;

import java.util.List;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/1/19 23:05
 */
public interface PermissionService {
    List<Permission> selectByUserId(Long userd);
}