package com.example.demo.bounded_context.wiki.dto;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.solution.entity.Category;
import com.example.demo.bounded_context.solution.entity.Tag;
import com.example.demo.bounded_context.solution.entity.Waste;
import com.example.demo.bounded_context.wiki.entity.Wiki;
import com.example.demo.bounded_context.wiki.entity.WikiState;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

public record WikiResponse (
        Long accountId,
        String accountNickname,
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
                        .map(Account::getId)
                        .orElse(null),
                Optional.ofNullable(writer)
                        .map(Account::getAccountName)
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
    public static WikiResponse fromEntity(Waste waste){
        Account writer = waste.getWriter();
        return new WikiResponse(
                Optional.ofNullable(writer)
                        .map(Account::getId)
                        .orElse(null),
                Optional.ofNullable(writer)
                        .map(Account::getAccountName)
                        .orElse(null),
                waste.getName(),
                waste.getCategories().stream()
                        .map(Category::getName)
                        .collect(Collectors.joining(",")),
                waste.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.joining(",")),
                waste.getSolution(),
                WikiState.ACCEPTED,
                waste.getCreatedDate(),
                waste.getModifiedDate()
        );
    }
}
