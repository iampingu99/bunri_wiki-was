package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.solution.entity.Wiki;
import com.example.demo.bounded_context.solution.entity.Waste;

public record WikiRequest (
        String name,
        String solution,
        String categories,
        String tags
){
    public Wiki toEntity(Account writer, Waste waste){
        return Wiki.builder()
                .writer(writer)
                .waste(waste)
                .name(name)
                .categories(categories)
                .tags(tags)
                .solution(solution)
                .build();
    }
}
