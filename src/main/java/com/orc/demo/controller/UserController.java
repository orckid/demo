package com.orc.demo.controller;

import com.alibaba.fastjson.JSON;
import com.orc.demo.common.LogOptEnum;
import com.orc.demo.common.User;
import com.orc.demo.service.UserService;
import com.orc.demo.util.annotation.LogAnnotation;
import com.orc.demo.util.kafka.KafkaProducer;
import com.orc.demo.util.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author orckid
 */
@Controller
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final RedisService redisService;

    public UserController(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    /*@Autowired
    private KafkaProducer kafkaProducer;*/

    @RequestMapping("/add")
    @ResponseBody
    @LogAnnotation(optEnum = LogOptEnum.A, annoValue = "添加用户")
    public String add(@Param("userName") String userName) {
        LOG.info("添加用户");
        if (StringUtils.isBlank(userName)) {
            return "请传入正确的用户名！";
        }
        User userValidate = userService.queryUserByUserName(userName);
        if (userValidate != null) {
            return "添加失败，用户名已存在，请重新添加！";
        }
        User user = new User();
        user.setUserName(userName);
        user.setEmail("18362956552@163.com");
        user.setInfo("huaxiaoxiao");
        userService.add(user);
        redisService.set(userName, JSON.toJSONString(user));
        return "添加成功,用戶名：" + user.getUserName();
    }

    @RequestMapping("/update")
    @ResponseBody
    @LogAnnotation(optEnum = LogOptEnum.U, annoValue = "更新用户")
    public String update(@Param("userName") String userName) {
        LOG.info("更新用戶,用戶名：{}", userName);
        String phone = "18362956552";
        String address = "梨树园路9号";
        LOG.info("update方法当前请求的线程名称为：{}", Thread.currentThread().getName());
        userService.update(phone, address, userName);
        return "更新成功，用戶名：" + userName;
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@Param("userName") String userName) {
        LOG.info("查找用戶信息,用戶名：{}", userName);
        LOG.info("query方法当前请求的线程名称为：{}", Thread.currentThread().getName());
        User user;
        String userStr =  redisService.get(userName);
        if (userStr == null) {
            LOG.info("redis中不存在，查询数据库");
            user = userService.queryUserByUserName(userName);
            return user == null ? "用户不存在" : user.toString();
        }
        LOG.info("从redis中获取成功。");

        // kafkaProducer.send(userName);
        user = JSON.parseObject(userStr, User.class);

        return user.toString();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@Param("userName") String userName) {
        LOG.info("删除用戶信息,用戶名：{}", userName);
        User user = userService.queryUserByUserName(userName);
        if (user == null) {
            return "用户不存在";
        }
        userService.deleteUser(userName);
        if (redisService.exist(userName)) {
            redisService.delete(userName);
        }

        return "删除成功，删除的用户信息：" + user.toString();
    }
}
