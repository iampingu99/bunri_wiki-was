package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.ContributedCreationState;
import com.example.demo.bounded_context.solution.entity.Waste;

import java.util.List;

public record ContributeCreationResponse(
        String nickName,
        String solutionName,
        String imageUrl,
        List<CategoryResponse> categories,
        List<TagResponse> tags,
        String solution,
        ContributedCreationState state
) {
    public static ContributeCreationResponse fromEntity(Waste waste) {
        return new ContributeCreationResponse(
                waste.getWriter().getNickname(),
                waste.getName(),
                waste.getImageUrl(),
                waste.getCategories().stream().map(CategoryResponse::fromEntity).toList(),
                waste.getTags().stream().map(TagResponse::fromEntity).toList(),
                waste.getSolution(),
                waste.getState()
        );
    }
}
