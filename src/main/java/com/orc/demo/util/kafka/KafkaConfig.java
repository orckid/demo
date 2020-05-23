package com.orc.demo.util.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * 创建消息topics 当需要创建的topic不存在时，自动创建。
 * @author orcki
 */
// @Configuration
public class KafkaConfig {
    @Bean
    public KafkaAdmin admin(KafkaProperties properties) {
        KafkaAdmin admin = new KafkaAdmin(properties.buildAdminProperties());
        admin.setFatalIfBrokerNotAvailable(true);

        return admin;
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("hello-1", 1, (short) 1);
    }

    @Bean
    public NewTopic topicNew() {
        return new NewTopic("hello", 1, (short) 1);
    }

}
