package com.luffy.view.service.kafkaMq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Consumer {
    @KafkaListener(topics = {"test"}, containerFactory = "kafkaListenerContainerFactory")
    public void listen(List<ConsumerRecord> records, Acknowledgment ack) {
        try {
            for (ConsumerRecord record : records) {
                System.out.println(String.format("offset=%d,ke=%s,value=%s\n", record.offset(), record.key(), record.value()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ack.acknowledge();
        }
    }
}
