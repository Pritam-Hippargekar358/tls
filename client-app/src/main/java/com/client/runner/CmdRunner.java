package com.client.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
public class CmdRunner implements CommandLineRunner {
    @Value("${server.base-url}")
    private String baseUrl;

    @Value("${server.endpoint}")
    private String endpoint;

    private final RestClient restClient;

    public CmdRunner(RestClient restClient) {
        this.restClient = restClient;
    }
    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<String> response =  restClient.get()
                .uri(baseUrl+endpoint)
                .retrieve()
                .toEntity(String.class);
        log.info("Message received at Client App: {}, {}",response.getStatusCode().value(),response.getBody());

    }

}
