package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.ContributedCreationState;
import com.example.demo.bounded_context.solution.entity.Waste;

import java.time.LocalDateTime;

public record ContributeCreationListResponse(
        Long wastId,
        String nickName,
        String name,
        ContributedCreationState contributedCreationState,
        LocalDateTime createdDate
) {
    public static ContributeCreationListResponse fromEntity(Waste waste){
        return new ContributeCreationListResponse(
                waste.getId(),
                waste.getWriter().getNickname(),
                waste.getName(),
                waste.getState(),
                waste.getCreatedDate()
        );
    }
}
