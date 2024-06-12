package com.example.demo.bounded_context.solution.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class DetectResponse {
    String responseTime;
    List<DetectResult> result;

    @Getter
    public static class DetectResult {
        private String name;
        private String imageUrl;
        @JsonProperty("class")
        private String className;
        private double confidence;
        private Map<String, Double> box;

        public void setImageUrl(String imageUrl){
            this.imageUrl = imageUrl;
        }
    }
}
