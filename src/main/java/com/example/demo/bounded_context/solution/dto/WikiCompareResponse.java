package com.example.demo.bounded_context.solution.dto;

public record WikiCompareResponse(
        WikiResponse origin,
        WikiResponse modified
) {
    public static WikiCompareResponse of(WikiResponse origin, WikiResponse modified){
        return new WikiCompareResponse(origin, modified);
    }
}
