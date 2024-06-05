package com.example.demo.bounded_context.wiki.dto;

import com.example.demo.bounded_context.wiki.entity.Wiki;
import com.example.demo.bounded_context.wiki.entity.WikiState;

import java.time.LocalDateTime;

public record WikiResponse (
        String writerName,
        String wasteName,
        String categories,
        String tags,
        String solution,
        WikiState wikiState,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
){
    public static WikiResponse fromEntity(Wiki wiki){
        return new WikiResponse(
                wiki.getWriter().getNickname(),
                wiki.getWaste().getName(),
                wiki.getCategories(),
                wiki.getTags(),
                wiki.getSolution(),
                wiki.getWikiState(),
                wiki.getCreatedDate(),
                wiki.getModifiedDate()
        );
    }
}
