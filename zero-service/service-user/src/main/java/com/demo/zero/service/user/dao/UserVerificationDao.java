package com.demo.zero.service.user.dao;

import com.demo.zero.user.domain.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserVerificationDao extends JpaRepository<UserVerification,String> {
    UserVerification getByUsername(String username);
    UserVerification getByPhone(String phone);
}
