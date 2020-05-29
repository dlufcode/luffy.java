package com.luffy.view.service.rabbitMq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue("myDirectQueue"),
        exchange = @Exchange(value = "myDirectExchange", type = ExchangeTypes.DIRECT),
        key = "mine.direct"
))
public class DirectConsumer {
    @RabbitHandler
    public void onMessage(@Payload String msg) {
        System.out.println(msg);
    }
}
