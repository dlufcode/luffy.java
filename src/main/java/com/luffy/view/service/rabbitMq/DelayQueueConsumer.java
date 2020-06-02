package com.luffy.view.service.rabbitMq;

import com.luffy.view.annotation.MethodEnd;
import com.luffy.view.config.mq.RabbitDelayQueueConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DelayQueueConsumer {


    /**
     * containerFactory rabbitListenerContainerFactory,myFactory
     *
     * @param message
     * @param channel
     * @param messageInfo
     */
    @RabbitListener(queues = RabbitDelayQueueConfig.DELAY_QUEUE_NAME, containerFactory = "myFactory")
    @RabbitHandler
    @MethodEnd(name = "DelayQueueConsumer")
    public void consumer(String message, Channel channel, Message messageInfo) {
        try {
            // false 移除队列  true 不移除
            channel.basicReject(messageInfo.getMessageProperties().getDeliveryTag(), false);
            System.out.println(String.format("%s:%s", new Date().toString(), message));
        } catch (Exception e) {
            System.out.println("exception:" + e.getMessage());
        }

    }

}
