package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.Tag;

public record TagResponse(
        String name
) {
    public static TagResponse fromEntity(Tag tag){
        return new TagResponse(tag.getName());
    }
}
