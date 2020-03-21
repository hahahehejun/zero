package com.demo.zero.service.user.service.impl;

import com.demo.zero.service.user.dao.UserVerificationDao;
import com.demo.zero.user.domain.UserVerification;
import com.demo.zero.user.api.UserVerificationService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class UserVerificationServiceImpl implements UserVerificationService {
    @Autowired
    private UserVerificationDao userVerificationDao;

    @Override
    public UserVerification getByUsername(String username) {
        return userVerificationDao.getByUsername(username);
    }

    @Override
    public UserVerification getByPhone(String phonne) {
        return userVerificationDao.getByPhone(phonne);
    }

    @Override
    public UserVerification save(UserVerification userVerification) {
        return userVerificationDao.save(userVerification);
    }


}
