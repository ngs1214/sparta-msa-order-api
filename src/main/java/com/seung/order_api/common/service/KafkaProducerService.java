package com.seung.order_api.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public KafkaProducerService(
            KafkaTemplate<String, String> kafkaTemplate,
            @Value("${spring.kafka.topic}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendMessage(Long orderId) {
        try{
            kafkaTemplate.send(this.topic, String.valueOf(orderId));
            log.info("Message sent successfully to topic: {}, value: {}",topic, orderId);
        }catch (Exception e){
            log.error("Error publishing order created event", e);
            throw new RuntimeException("Failed to publish order event", e);
        }
    }
}
