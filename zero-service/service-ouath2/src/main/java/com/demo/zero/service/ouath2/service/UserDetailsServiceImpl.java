package com.demo.zero.service.ouath2.service;


import com.demo.zero.user.api.PermissionService;
import com.demo.zero.user.api.UserVerificationService;
import com.demo.zero.user.domain.Permission;
import com.demo.zero.user.domain.UserVerification;
import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义认证
 * <p>
 * Description:
 * </p>
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0")
    private UserVerificationService userVerificationService;

    @Reference(version = "1.0.0")
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 查询用户
        UserVerification  userVerifcation = userVerificationService.getByUsername(s);
        List<GrantedAuthority> grantedAuthorities= Lists.newArrayList();
        if(userVerifcation != null) {
            //当用户存在时查询用户所有权限并返回
            List<Permission> permissions = permissionService.selectByUserId(userVerifcation.getId());
            permissions.forEach(permission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getEnname());
                grantedAuthorities.add(grantedAuthority);
            });
            return new User(userVerifcation.getUsername(),userVerifcation.getPassword(),grantedAuthorities);
        }
        return null;

    }

}
