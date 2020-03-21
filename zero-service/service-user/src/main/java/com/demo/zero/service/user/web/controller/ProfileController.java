package com.demo.zero.service.user.web.controller;

import com.demo.zero.commons.dto.BaseResult;
import com.demo.zero.service.user.dto.PasswordParam;
import com.demo.zero.user.api.UserProfileService;
import com.demo.zero.user.api.UserVerificationService;
import com.demo.zero.user.domain.UserProfile;
import com.demo.zero.user.domain.UserVerification;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 *
 * @author: 哈哈呵呵君
 * @date: 2020/2/5 05:40
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Resource
    private UserProfileService userProfileService;
    @Resource
    private UserVerificationService userVerificationService;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;


    /**
     * 获取用户信息
     *
     * @return BaseResult
     */
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/info/{username}")
    @ApiOperation(value = "获取用户相关信息", notes = "获取用户信息（用户名，头像等）")
    public BaseResult info(@PathVariable String username) {

        UserProfile userProfile = userProfileService.getByUsername(username);
        if (Objects.isNull(userProfile)){
            return BaseResult.fail("不存在该用户");
        }

        return BaseResult.success("获取成功",userProfile);
    }

    /**
     * 更新个人信息
     *
     * @param userProfile
     * @return BaseResult
     */
    @PostMapping(value = "update")
    public BaseResult update(@RequestBody UserProfile userProfile) {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getName().equals(userProfile.getUsername())){
            return BaseResult.fail("不能修改用户名");
        }
        userProfileService.update(userProfile);
        return BaseResult.success("更新成功");
    }

    /**
     * 修改密码
     *
     * @param passwordParam
     * @return BaseResult
     */
    @PostMapping(value = "modify/password")
    public BaseResult modifyPassword(@RequestBody PasswordParam passwordParam) {

        UserVerification userVerification = userVerificationService.getByUsername(passwordParam.getUsername());

        // 旧密码正确
        if (passwordEncoder.matches(passwordParam.getOldPassword(), userVerification.getPassword())) {
            userVerification.setPassword(passwordEncoder.encode(passwordParam.getNewPassword()));
            userVerificationService.save(userVerification);
            return BaseResult.success("修改成功");
        }

        // 旧密码错误
        else {
            return BaseResult.fail("旧密码不匹配，请重试");
        }
    }


}
