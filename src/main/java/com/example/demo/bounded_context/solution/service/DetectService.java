package com.example.demo.bounded_context.solution.service;

import com.example.demo.bounded_context.solution.dto.DetectRequest;
import com.example.demo.bounded_context.solution.dto.DetectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DetectService {

    @Value("${server.detect-api}")
    private String pythonServerUrl;

    private final WebClient webClient = WebClient.builder()
            .baseUrl(pythonServerUrl)
            .build();

    public DetectResponse imageDetection(DetectRequest request) {

        return webClient.method(HttpMethod.GET)
                .uri("/models/image-detection")
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    throw new RuntimeException("4xx");
                })
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    throw new RuntimeException("5xx");
                })
                .bodyToMono(DetectResponse.class)
                .block();
    }
}
