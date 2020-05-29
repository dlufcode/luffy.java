package com.luffy.view.service.rabbitMq;

import com.luffy.view.config.mq.RabbitDelayQueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DelayQueueConsumer {

    @RabbitListener(queues = RabbitDelayQueueConfig.DELAY_QUEUE_NAME)
    @RabbitHandler
    public void consumer(String message) {
        System.out.println(String.format("%s:%s", new Date().toString(), message));
    }

}
