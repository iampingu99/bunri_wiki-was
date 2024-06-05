package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.ContributedCreationState;
import com.example.demo.bounded_context.solution.entity.Waste;

import java.time.LocalDateTime;

public record ContributedCreationsResponse(
        String name,
        ContributedCreationState contributedCreationState,
        LocalDateTime createdDate
) {
    public static ContributedCreationsResponse fromEntity(Waste waste){
        return new ContributedCreationsResponse(
                waste.getName(),
                waste.getState(),
                waste.getCreatedDate()
        );
    }
}
