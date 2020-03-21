package com.demo.zero.commodity.api;

import com.demo.zero.commodity.domain.Commodity;
import com.demo.zero.commodity.domain.CommodityUser;

import java.util.List;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/6 23:40
 */
public interface CommodityService {
    List<Commodity> getByUserId(Long userId);
    Commodity getById(Long id);
    void save(Commodity commodity, CommodityUser commodityUser);
}
