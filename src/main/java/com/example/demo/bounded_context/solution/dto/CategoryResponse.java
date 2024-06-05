package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.Category;

public record CategoryResponse (
        String name
){
    public static CategoryResponse fromEntity(Category category){
        return new CategoryResponse(category.getName());
    }
}
