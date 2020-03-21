package com.demo.zero.user.api;

import com.demo.zero.user.domain.UserVerification;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/1/19 23:05
 */
public interface UserVerificationService {
    public UserVerification getByUsername(String username);
    public UserVerification getByPhone(String phonne);
    public UserVerification save(UserVerification userVerification);
}