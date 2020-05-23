package com.orc.demo.util.kafka;

import com.alibaba.fastjson.JSON;
import com.orc.demo.common.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author orcki
 */
// @Component
public class KafkaProducer {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String userName) {
        LOG.info("kafka开始生产消息...");
        User user = new User();
        user.setUserName(userName);
        user.setEmail("18362956552@163.com");
        user.setInfo(UUID.randomUUID().toString());

        kafkaTemplate.send("hello-1", JSON.toJSONString(user));
        kafkaTemplate.send("hello", JSON.toJSONString(user));
        LOG.info("kafka消息已生成...");
    }

}
