package com.demo.zero.service.commodity.web.controller;

import com.demo.zero.commodity.api.CommodityService;
import com.demo.zero.commodity.domain.Commodity;
import com.demo.zero.commodity.domain.CommodityUser;
import com.demo.zero.commons.dto.BaseResult;
import com.demo.zero.user.api.UserVerificationService;
import com.demo.zero.user.domain.UserVerification;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/7 00:05
 */
@RestController
@RequestMapping("commodity")
public class CommodityController {
    @Resource
    private CommodityService commodityService;

    @Reference(version = "1.0.0")
    private UserVerificationService userVerificationService;

    @GetMapping("")
    public BaseResult getByUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserVerification userVerification= userVerificationService.getByUsername(authentication.getName());
        List<Commodity> commodities = commodityService.getByUserId(userVerification.getId());
        return BaseResult.success("查询成功",commodities);

    }

    @GetMapping("/detail/{id}")
    public BaseResult getById(@PathVariable Long id){

        Commodity commoditie = commodityService.getById(id);
        return BaseResult.success("查询成功",commoditie);

    }

    @PostMapping("")
    public BaseResult save(@RequestBody Commodity commodity) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserVerification userVerification= userVerificationService.getByUsername(authentication.getName());
        CommodityUser commodityUser = new CommodityUser();
        commodityUser.setUserId(userVerification.getId());
        commodityService.save(commodity,commodityUser);
        return BaseResult.success("提交成功");
    }


}
