package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.Tag;
import com.example.demo.bounded_context.solution.entity.Waste;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "SolutionListResponse", description = "솔루션 목록 조회 DTO")
public record SolutionListResponse(
    Long id,
    String name,
    List<String> tags,
    String imageUrl
){
    public static SolutionListResponse fromEntity(Waste waste){
        return new SolutionListResponse(
                waste.getId(),
                waste.getName(),
                waste.getTags().stream().map(Tag::getName).toList(),
                waste.getImageUrl()
        );
    }
}
