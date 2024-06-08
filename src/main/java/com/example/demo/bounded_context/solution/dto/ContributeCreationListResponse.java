package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.ContributedCreationState;
import com.example.demo.bounded_context.solution.entity.Waste;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "ContributeCreationListResponse", description = "사용자 생성 요청 목록 DTO")
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
