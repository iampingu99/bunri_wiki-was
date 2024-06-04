package com.example.demo.bounded_context.wiki.dto;

import com.example.demo.bounded_context.wiki.entity.Wiki;
import com.example.demo.bounded_context.wiki.entity.WikiState;

import java.time.LocalDateTime;

public record WikiListResponse(
        Long wikiId,
        String authorNickName,
        WikiState wikiState,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {
    public static WikiListResponse fromEntity(Wiki wiki){
        return new WikiListResponse(
                wiki.getId(),
                wiki.getWriter().getNickname(),
                wiki.getWikiState(),
                wiki.getCreatedDate(),
                wiki.getModifiedDate()
        );
    }
}
