package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.Waste;

public record WasteListResponse(
    Long id,
    String name,
    String imageUrl
){
    public static WasteListResponse fromEntity(Waste waste){
        return new WasteListResponse(
                waste.getId(),
                waste.getName(),
                waste.getImageUrl()
        );
    }
}
