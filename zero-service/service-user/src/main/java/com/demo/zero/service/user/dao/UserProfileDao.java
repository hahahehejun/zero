package com.demo.zero.service.user.dao;

import com.demo.zero.user.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/1/28 02:22
 */
public interface UserProfileDao extends JpaRepository<UserProfile,String> {
    UserProfile getByUsername(String username);
    UserProfile getByPhone(String phone);
}
