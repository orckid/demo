package com.orc.demo.mapper.user;

import com.orc.demo.common.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author orcki
 */
@Repository
public interface UserMapper {
    /**
     * 添加用户
     * @param user 用户信息
     */
    void add(@Param("user") User user);

    /**
     * 更新用户信息
     * @param phone 手机号
     * @param address 地址
     * @param userName 更新的用户
     */
    void update(@Param("phone") String phone, @Param("address") String address, @Param("userName") String userName);

    /**
     * 查找用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    User queryUser(@Param("userName") String userName);

    /**
     * 删除用户
     * @param userName 用户名
     */
    void deleteUser(@Param("userName") String userName);
}
