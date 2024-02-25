package com.example.loadbalencer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoadBalancerController {

    private final RestTemplate restTemplate;
    private final String backendUrl;

    @Autowired
    public LoadBalancerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.backendUrl = "http://localhost:8081"; // Replace with your backend server URL
    }

    @GetMapping("/")
    public String forwardRequest() {
        ResponseEntity<String> response = restTemplate.getForEntity(backendUrl, String.class);
        return "Response from server: " + response.getStatusCode() + "\n\n" + response.getBody();
    }
}
