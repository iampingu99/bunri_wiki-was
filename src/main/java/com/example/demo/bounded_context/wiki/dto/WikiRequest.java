package com.example.demo.bounded_context.wiki.dto;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.wiki.entity.Wiki;
import com.example.demo.bounded_context.solution.entity.Waste;

public record WikiRequest (
        String name,
        String solution,
        String categories,
        String tags
){
    public Wiki toEntity(Account writer, Waste waste, Wiki parentWiki){
        return Wiki.builder()
                .writer(writer)
                .waste(waste)
                .parent(parentWiki)
                .name(name)
                .categories(categories)
                .tags(tags)
                .solution(solution)
                .build();
    }
}
