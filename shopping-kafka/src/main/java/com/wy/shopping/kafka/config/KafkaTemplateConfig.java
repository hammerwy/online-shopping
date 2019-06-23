package com.wy.shopping.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * @author wy
 * @date 2019-06-23
 * @description kafka生产与消费配置类
 */
@Configuration
@EnableKafka
public class KafkaTemplateConfig {
    /**
     * 配置kafka
     *
     * @return kafkaTemplate
     */
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate(DefaultKafkaProducerFactory producerFactory) {
//        return new KafkaTemplate<>(producerFactory);
//    }
}
