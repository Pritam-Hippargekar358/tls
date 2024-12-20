package com.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;

@RestController
@Slf4j
public class ClientController {
    @Value("${server.base-url}")
    private String baseUrl;

    @Value("${server.endpoint}")
    private String endpoint;

    private final RestClient restClient;

    public ClientController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("/api/client")
    public ResponseEntity<String> helloClient() {
        log.info("Message received at Client App");
        ResponseEntity<String> response =  restClient.get()
                .uri(baseUrl+endpoint)
                .retrieve()
                .toEntity(String.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}
