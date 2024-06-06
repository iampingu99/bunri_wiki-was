package com.example.demo.bounded_context.solution.dto;

public record DetectRequest(
        String type,
        String resource

) {
    public static DetectRequest fromEntity(String imageUrl){
        return new DetectRequest("url", imageUrl);
    }
}
