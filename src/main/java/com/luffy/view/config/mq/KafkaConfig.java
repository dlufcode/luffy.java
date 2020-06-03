package com.luffy.view.config.mq;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties")
@Data
public class KafkaConfig {

    @Value("${spring.kafka.topic}")
    private String topic;
}
