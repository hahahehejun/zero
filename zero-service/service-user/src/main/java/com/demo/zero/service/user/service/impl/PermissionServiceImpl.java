package com.demo.zero.service.user.service.impl;

import com.demo.zero.service.user.dao.PermissionDao;
import com.demo.zero.user.domain.Permission;
import com.demo.zero.user.api.PermissionService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/1/18 21:16
 */
@Service(version = "1.0.0")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> selectByUserId(Long userid) {
        return permissionDao.selectByUserId(userid);
    }
}
