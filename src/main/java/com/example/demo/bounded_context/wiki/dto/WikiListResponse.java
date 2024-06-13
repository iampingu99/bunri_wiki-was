package com.example.demo.bounded_context.wiki.dto;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.wiki.entity.Wiki;
import com.example.demo.bounded_context.wiki.entity.WikiState;

import java.time.LocalDateTime;
import java.util.Optional;

public record WikiListResponse(
        Long wikiId,
        String accountNickname,
        Long accountId,
        String wasteName,
        WikiState wikiState,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {
    public static WikiListResponse fromEntity(Wiki wiki){
        Account writer = wiki.getWriter();
        return new WikiListResponse(
                wiki.getId(),
                Optional.ofNullable(writer)
                        .map(Account::getAccountName)
                        .orElse(null),
                Optional.ofNullable(writer)
                        .map(Account::getId)
                        .orElse(null),
                wiki.getWaste().getName(),
                wiki.getWikiState(),
                wiki.getCreatedDate(),
                wiki.getModifiedDate()
        );
    }
}
