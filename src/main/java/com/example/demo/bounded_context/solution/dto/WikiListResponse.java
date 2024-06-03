package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.Wiki;

import java.time.LocalDateTime;

public record WikiListResponse(
        Long wikiId,
        LocalDateTime createdAt,
        String writer,
        Boolean isAccept
) {
    public static WikiListResponse fromEntity(Wiki wiki){
        return new WikiListResponse(
                wiki.getId(),
                wiki.getCreatedDate(),
                wiki.getWriter().getAccountName(),
                wiki.getIsAccept()
        );
    }
}
