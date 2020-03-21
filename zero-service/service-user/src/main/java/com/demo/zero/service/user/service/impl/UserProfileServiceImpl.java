package com.demo.zero.service.user.service.impl;

import com.demo.zero.service.user.dao.UserProfileDao;
import com.demo.zero.user.api.UserProfileService;
import com.demo.zero.user.domain.UserProfile;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/1/28 02:26
 */
@Service(version = "1.0.0")
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    public UserProfile getByUsername(String username) {
        return userProfileDao.getByUsername(username);
    }

    @Override
    public UserProfile getByPhone(String phonne) {
        return null;
    }

    @Override
    public void save(UserProfile userProfile) {
        userProfileDao.save(userProfile);
    }

    /**
     * 更新用户
     * <p>
     * 仅允许更新 邮箱、昵称、备注、状态
     * </p>
     *
     * @param userProfile
     * @return {@code int} 大于 0 则表示更新成功
     */
    @Override
    public int update(UserProfile userProfile) {
        userProfileDao.save(userProfile);
        return 1;
    }
}
