package com.example.loadbalencer;

import com.example.loadbalencer.controller.LoadBalancerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LoadBalancerControllerTest {

    @Mock
    private RestTemplate restTemplate;

    private LoadBalancerController loadBalancerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        // Mock backend server URLs
        List<String> backendUrls = Arrays.asList("http://localhost:8081", "http://localhost:8082");
        loadBalancerController = new LoadBalancerController(restTemplate, backendUrls);
    }

    @Test
    public void testForwardRequest() {
        // Mock response from backend server
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Hello from the backend server", HttpStatus.OK);
        when(restTemplate.getForEntity("http://localhost:8081", String.class)).thenReturn(responseEntity);

        // Test forwardRequest method
        String result = loadBalancerController.forwardRequest();

        // Verify the result
        assertEquals("Response from server http://localhost:8081:\nHello from the backend server", result);
    }
}
