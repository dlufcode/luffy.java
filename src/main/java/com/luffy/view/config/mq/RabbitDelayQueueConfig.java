package com.luffy.view.config.mq;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDelayQueueConfig {
    public final static String DELAY_QUEUE_TTL_NAME = "delay_queue_ttl";
    public final static String DELAY_QUEUE_NAME = "delay_queue";
    public final static String DELAY_EXCHANGE = "delay_exchange";

    public final static int DELAY_EXPIRATION = 4000;

    @Bean
    Queue delayQueueTTL() {
        return QueueBuilder.durable(DELAY_QUEUE_TTL_NAME)
                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DELAY_QUEUE_NAME)
                .withArgument("x-message-ttl", DELAY_EXPIRATION)
                .build();
    }

    @Bean
    Queue delayQueue() {
        return QueueBuilder.durable(DELAY_QUEUE_NAME)
                .build();
    }

    @Bean
    DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE);
    }

    @Bean
    Binding dlxBinding(Queue delayQueue, DirectExchange delayExchange) {
        return BindingBuilder.bind(delayQueue)
                .to(delayExchange)
                .with(DELAY_QUEUE_NAME);
    }

}
