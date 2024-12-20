package com.server.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.server.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;

@Component
@Slf4j
public class CmdRunner implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        UserDto dto = new UserDto();
        dto.setUserName("Ayushman **");
        dto.setRoles(Arrays.asList("Ayushman1","Ravan1"));
        SendResult<String, String> result = kafkaTemplate.send("user-topic", objectMapper.writeValueAsString(dto)).get();
        System.out.println(result.getRecordMetadata().offset());
        System.out.println(result.getRecordMetadata().partition());
    }
}
