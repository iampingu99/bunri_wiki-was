package com.example.demo.bounded_context.wiki.dto;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.wiki.entity.Wiki;
import com.example.demo.bounded_context.wiki.entity.WikiState;

import java.time.LocalDateTime;
import java.util.Optional;

public record WikiListResponse(
        Long wikiId,
        String authorNickName,
        String wasteName,
        WikiState wikiState,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {
    public static WikiListResponse fromEntity(Wiki wiki){
        return new WikiListResponse(
                wiki.getId(),
                Optional.ofNullable(wiki.getWriter())
                        .map(Account::getAccountName)
                        .orElse(null),
                wiki.getWaste().getName(),
                wiki.getWikiState(),
                wiki.getCreatedDate(),
                wiki.getModifiedDate()
        );
    }
}
