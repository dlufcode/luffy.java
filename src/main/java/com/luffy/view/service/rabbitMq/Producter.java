package com.luffy.view.service.rabbitMq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producter {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String exchange, String routkey, String message) {
        if (routkey == null) {
            rabbitTemplate.convertAndSend(exchange, message);
        } else {
            rabbitTemplate.convertAndSend(exchange, routkey, message);
        }

    }
}
