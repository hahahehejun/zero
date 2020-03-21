package com.demo.zero.user.api;

import com.demo.zero.user.domain.UserProfile;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/1/28 02:21
 */
public interface UserProfileService {
    UserProfile getByUsername(String username);
    UserProfile getByPhone(String phonne);
    void save(UserProfile userProfile);
    /**
     * 更新用户
     * <p>
     * 仅允许更新 邮箱、昵称、备注、状态
     * </p>
     *
     * @param userProfile
     * @return {@code int} 大于 0 则表示更新成功
     */
    int update(UserProfile userProfile);
}
