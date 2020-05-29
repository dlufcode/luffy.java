package com.luffy.view.service.rabbitMq;

import com.luffy.view.config.mq.RabbitDelayQueueConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DelayQueueProducter {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend(RabbitDelayQueueConfig.DELAY_QUEUE_TTL_NAME, message);
    }
}
