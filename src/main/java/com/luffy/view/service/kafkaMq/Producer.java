package com.luffy.view.service.kafkaMq;

import com.luffy.view.config.mq.KafkaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.UUID;

@Component
public class Producer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaConfig kafkaConfig;

    public String send(String message) {
        String id = UUID.randomUUID().toString();
        ListenableFuture listenableFuture = kafkaTemplate.send(kafkaConfig.getTopic(), id, message);
        //
        SuccessCallback successCallback = new SuccessCallback() {
            @Override
            public void onSuccess(Object o) {
                System.out.println(String.format("%s:%s", id, "发送成功！"));
            }
        };
        //
        FailureCallback failureCallback = new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(String.format("%s:%s", id, "发送失败"));
            }
        };

        listenableFuture.addCallback(successCallback, failureCallback);
        return "kafka producer send done";
    }
}
