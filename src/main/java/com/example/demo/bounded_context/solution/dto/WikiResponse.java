package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.Wiki;

import java.time.LocalDateTime;

public record WikiResponse (
        String writerName,
        String wasteName,
        String solution,
        String categories,
        String tags,
        LocalDateTime createdAt
){
    public static WikiResponse fromEntity(Wiki wiki){
        return new WikiResponse(
                wiki.getWriter().getNickname(),
                wiki.getName(),
                wiki.getSolution(),
                wiki.getCategories(),
                wiki.getTags(),
                wiki.getCreatedDate()
        );
    }
}
