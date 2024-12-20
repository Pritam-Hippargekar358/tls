package com.client.service;

import com.client.dto.UserDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @KafkaListener(topics = "user-topic", groupId = "reactive-group")
    public void consumer(String message
//            , Acknowledgment acknowledgment
    ) {
        System.out.println("-----------------------------------------------------------");
        System.out.println(message);
//        acknowledgment.acknowledge();
    }
}
