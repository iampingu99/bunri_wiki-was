package com.example.demo.bounded_context.solution.dto;

import com.example.demo.bounded_context.solution.entity.Waste;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SolutionRequest", description = "새 솔루션 생성 DTO")
public record SolutionRequest(
        @Schema(description = "대상 이름", example = "쇼핑백")
        String name,
        @Schema(description = "이미지 경로", example = "https://...")
        String imageUrl,
        @Schema(description = "카테고리", example = "종이류,비닐류")
        String categories,
        @Schema(description = "태그", example = "종이백,비닐쇼핑백")
        String tags,
        @Schema(description = "배출 솔루션", example = "일반 종이 재질의 쇼핑백은 종이로 분리배출\r\n비닐 쇼핑백은 비닐류로 분리배출")
        String solution
) {
    public Waste toEntity(){
        Waste waste = Waste.builder()
                .name(name)
                .solution(solution)
                .imageUrl(imageUrl)
                .build();
        waste.setTags(tags);
        waste.setCategories(categories);
        return waste;
    }
}
