package com.example.demo.bounded_context.wiki.dto;

import com.example.demo.bounded_context.solution.entity.ContributedCreationState;
import com.example.demo.bounded_context.wiki.entity.Wiki;
import com.example.demo.bounded_context.wiki.entity.WikiState;

import java.time.LocalDateTime;

public record ContributeModificationsResponse(
        Long wikiId,
        String wasteName,
        WikiState wikiState,
        LocalDateTime createdDate
) {
    public static ContributeModificationsResponse fromEntity(Wiki wiki){
        return new ContributeModificationsResponse(
                wiki.getId(),
                wiki.getWaste().getName(),
                wiki.getWikiState(),
                wiki.getCreatedDate()
        );
    }
}
