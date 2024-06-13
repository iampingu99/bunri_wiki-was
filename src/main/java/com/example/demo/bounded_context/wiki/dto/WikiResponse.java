package com.example.demo.bounded_context.wiki.dto;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.wiki.entity.Wiki;
import com.example.demo.bounded_context.wiki.entity.WikiState;

import java.time.LocalDateTime;
import java.util.Optional;

public record WikiResponse (
        String writerName,
        Long writerId,
        String wasteName,
        String categories,
        String tags,
        String solution,
        WikiState wikiState,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
){
    public static WikiResponse fromEntity(Wiki wiki){
        Account writer = wiki.getWriter();
        return new WikiResponse(
                Optional.ofNullable(writer)
                        .map(Account::getAccountName)
                        .orElse(null),
                Optional.ofNullable(writer)
                        .map(Account::getId)
                        .orElse(null),
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
