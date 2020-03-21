package com.demo.zero.service.commodity.service.impl;

import com.demo.zero.commodity.api.CommodityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/6 23:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommodityServiceImplTest {
    @Resource
    private CommodityService commodityService;

    @Test
    public void getByUserId(){
        System.out.println(commodityService.getByUserId(1L).size());
    }

}
