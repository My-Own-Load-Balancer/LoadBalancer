package com.example.loadbalencer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoadBalancerController {

    private final RestTemplate restTemplate;
    private final List<String> backendUrls;
    private int currentIndex;

    @Autowired
    public LoadBalancerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.backendUrls = new ArrayList<>();
        // Hardcoded backend server URLs for demonstration
        backendUrls.add("http://localhost:8081");
        backendUrls.add("http://localhost:8082");
        this.currentIndex = 0;
        System.out.println(backendUrls);
    }

    @GetMapping("/")
    public String forwardRequest() {
        String backendUrl = getNextBackendUrl();
        ResponseEntity<String> response = restTemplate.getForEntity(backendUrl, String.class);
        return "Response from server " + backendUrl + ":\n" + response.getBody();
    }

    private synchronized String getNextBackendUrl() {
        String nextBackendUrl = backendUrls.get(currentIndex);
        currentIndex = (currentIndex + 1) % backendUrls.size();
        return nextBackendUrl;
    }
}
