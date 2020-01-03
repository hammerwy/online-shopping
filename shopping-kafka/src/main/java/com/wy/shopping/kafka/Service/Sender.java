package com.wy.shopping.kafka.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wy
 * @date 2019-06-23
 * @description
 */
@Slf4j
@Service
public class Sender {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "test.topic";

    public void sendMsg(Integer index) {

        ListenableFuture send = kafkaTemplate.send(TOPIC, "test-message : " + index);
        send.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("第 " + index + " 条消息发送失败！");
            }

            @Override
            public void onSuccess(Object result) {
                log.info("第 " + index + " 条消息发送成功！");
            }
        });
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add(null);
        System.out.println(list.size());
    }
}
