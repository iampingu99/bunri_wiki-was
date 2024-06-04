package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.Wiki;
import com.example.demo.bounded_context.solution.entity.WikiState;

import java.time.LocalDateTime;

public record WikiResponse (
        String writerName,
        String wasteName,
        String solution,
        String categories,
        String tags,
        WikiState wikiState,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
){
    public static WikiResponse fromEntity(Wiki wiki){
        return new WikiResponse(
                wiki.getWriter().getNickname(),
                wiki.getName(),
                wiki.getSolution(),
                wiki.getCategories(),
                wiki.getTags(),
                wiki.getWikiState(),
                wiki.getCreatedDate(),
                wiki.getModifiedDate()
        );
    }
}
