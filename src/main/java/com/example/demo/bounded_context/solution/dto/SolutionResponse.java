package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.Category;
import com.example.demo.bounded_context.solution.entity.Tag;
import com.example.demo.bounded_context.solution.entity.Waste;

import java.util.List;

/**
 * [x] Set 자료구조 미사용으로 변경
 */
public record SolutionResponse(
        Long wasteId,
        String name,
        List<String> materials,
        List<String> tags,
        String solution,
        List<WikiListResponse> opinions
){
    public static SolutionResponse fromEntity(Waste waste){
        return new SolutionResponse(
                waste.getId(),
                waste.getName(),
                waste.getCategories().stream().map(Category::getName).toList(),
                waste.getTags().stream().map(Tag::getName).toList(),
                waste.getSolution(),
                waste.getWikis().stream().map(WikiListResponse::fromEntity).toList()
                );
    }
}
