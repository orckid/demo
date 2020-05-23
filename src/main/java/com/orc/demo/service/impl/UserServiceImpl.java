package com.orc.demo.service.impl;

import com.orc.demo.common.User;
import com.orc.demo.mapper.user.UserMapper;
import com.orc.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author orcki
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void add(User user) {
        LOG.info("service开始添加用户");
        userMapper.add(user);
    }

    @Override
    @Async
    public void update(String phone, String address, String userName) {
        LOG.info("service开始更新用户");
        LOG.info("update>>service方法当前请求的线程名称为：{}", Thread.currentThread().getName());
        userMapper.update(phone, address, userName);
    }

    @Override
    public User queryUserByUserName(String userName) {
        LOG.info("service开始查找用户");
        LOG.info("query>>service方法当前请求的线程名称为：{}", Thread.currentThread().getName());
        return userMapper.queryUser(userName);
    }

    @Override
    public void deleteUser(String userName) {
        LOG.info("service开始删除用户");
        userMapper.deleteUser(userName);
    }
}
