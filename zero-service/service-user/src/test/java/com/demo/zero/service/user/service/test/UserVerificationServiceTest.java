package com.demo.zero.service.user.service.test;

import com.demo.zero.service.user.dao.PermissionDao;
import com.demo.zero.user.api.UserVerificationService;
import com.demo.zero.user.domain.UserVerification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserVerificationServiceTest {

    @Autowired
    private PermissionDao permissionDao;
    @Resource
    private UserVerificationService userVerificationService;

    /**
     * 数据库连接测试
     */
    @Test
    public void jdbc_connect_test(){
        UserVerification userVerification = userVerificationService.getByUsername("jdbc_test");
        assertEquals(userVerification.getPassword(),"success");
    }


    /**
     * 多表查询测试
     */
    @Test
    public void selectByUserIdTest(){
        System.out.println(permissionDao.selectByUserId(1L));
    }
}
