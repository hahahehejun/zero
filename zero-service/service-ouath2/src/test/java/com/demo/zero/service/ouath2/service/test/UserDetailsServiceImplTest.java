package com.demo.zero.service.ouath2.service.test;

import com.demo.zero.user.api.PermissionService;
import com.demo.zero.user.api.UserVerificationService;
import com.demo.zero.user.domain.Permission;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/1/18 23:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsServiceImplTest {

    @Reference(version = "1.0.0")
    private UserVerificationService userVerificationService;
    @Reference(version = "1.0.0")
    private PermissionService permissionService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void test(){
        List<Permission> permissions = permissionService.selectByUserId(1l);
        System.out.println(permissions.size());
    }
    @Test
    public void getPassword(){
        System.out.println(passwordEncoder.encode("123456"));
    }

}
