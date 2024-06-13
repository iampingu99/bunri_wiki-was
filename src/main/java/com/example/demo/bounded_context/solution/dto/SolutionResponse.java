package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.solution.entity.Category;
import com.example.demo.bounded_context.solution.entity.ContributedCreationState;
import com.example.demo.bounded_context.solution.entity.Tag;
import com.example.demo.bounded_context.solution.entity.Waste;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Optional;

@Schema(name = "SolutionResponse", description = "솔루션 조회 DTO")
public record SolutionResponse(
        Long writerId,
        String writerNickName,
        String solutionName,
        String imageUrl,
        List<String> categories,
        List<String> tags,
        String solution,
        ContributedCreationState state
) {
    public static SolutionResponse fromEntity(Waste waste) {
        Account writer = waste.getWriter();
        return new SolutionResponse(
                Optional.ofNullable(writer)
                        .map(Account::getId)
                        .orElse(null),
                Optional.ofNullable(writer)
                        .map(Account::getAccountName)
                        .orElse(null),
                waste.getName(),
                waste.getImageUrl(),
                waste.getCategories().stream().map(Category::getName).toList(),
                waste.getTags().stream().map(Tag::getName).toList(),
                waste.getSolution(),
                waste.getState()
        );
    }
}
