package com.demo.zero.service.commodity.service.impl;

import com.demo.zero.commodity.api.CommodityService;
import com.demo.zero.commodity.domain.Commodity;
import com.demo.zero.commodity.domain.CommodityUser;
import com.demo.zero.service.commodity.dao.CommodityDao;
import com.demo.zero.service.commodity.dao.CommodityUserDao;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/6 23:42
 */
@Service(version = "1.0.0")
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityDao commodityDao;
    @Autowired
    private CommodityUserDao commodityUserDao;

    @Override
    public List<Commodity> getByUserId(Long userId) {
        return commodityDao.getByUserId(userId);
    }

    @Override
    public Commodity getById(Long id) {
        return commodityDao.getById(id);
    }

    @Override
    public void save(Commodity commodity, CommodityUser commodityUser) {
        Commodity reCommodity = commodityDao.save(commodity);
        commodityUser.setCommodityId(reCommodity.getId());
        commodityUserDao.save(commodityUser);
    }
}
