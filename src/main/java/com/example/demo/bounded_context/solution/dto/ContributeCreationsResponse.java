package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.ContributedCreationState;
import com.example.demo.bounded_context.solution.entity.Waste;

import java.time.LocalDateTime;

public record ContributeCreationsResponse(
        String name,
        ContributedCreationState contributedCreationState,
        LocalDateTime createdDate
) {
    public static ContributeCreationsResponse fromEntity(Waste waste){
        return new ContributeCreationsResponse(
                waste.getName(),
                waste.getState(),
                waste.getCreatedDate()
        );
    }
}
