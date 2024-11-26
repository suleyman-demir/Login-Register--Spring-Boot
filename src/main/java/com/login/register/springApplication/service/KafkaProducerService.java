package com.login.register.springApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "user-registered-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishUserRegisteredEvent(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Published message to Kafka: " + message);
    }
}
