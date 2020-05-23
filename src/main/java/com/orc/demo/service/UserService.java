package com.orc.demo.service;

import com.orc.demo.common.User;

/**
 * @author orcki
 */
public interface UserService {

    /**
     * 添加用户
     * @param user 用户信息
     */
    void add(User user);

    /**
     * 更新用户
     * @param phone 手机号
     * @param address 地址
     * @param userName 用户名
     */
    void update(String phone, String address, String userName);

    /**
     * 根据用户名查找用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    User queryUserByUserName(String userName);

    /**
     * 删除用户
     * @param userName 用户名
     */
    void deleteUser(String userName);
}
