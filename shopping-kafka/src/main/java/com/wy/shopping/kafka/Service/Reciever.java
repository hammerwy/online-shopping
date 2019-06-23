package com.wy.shopping.kafka.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author wy
 * @date 2019-06-24
 * @description
 */
@Slf4j
@Service
public class Reciever {
    @KafkaListener(topics = "test.topic")
    public void receive(@Payload String message, @Headers MessageHeaders headers) {
        log.info("接收到消息：{}", message);
        headers.keySet().forEach(key -> log.info("{}: {}", key, headers.get(key)));
    }
}
