package com.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ServerController {

    @GetMapping("/api/server")
    public ResponseEntity<String> helloServer() {
        log.info("Message received at Server App");
        return ResponseEntity.ok("Hello World !! from Server App");
    }
}
