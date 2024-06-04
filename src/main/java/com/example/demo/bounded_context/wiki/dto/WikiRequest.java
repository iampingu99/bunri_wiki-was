package com.example.demo.bounded_context.wiki.dto;

import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.wiki.entity.Wiki;
import com.example.demo.bounded_context.solution.entity.Waste;
import io.swagger.v3.oas.annotations.media.Schema;

//trim 필요
@Schema(name = "WikiCreateRequest", description = "위키 생성 요청 DTO")
public record WikiRequest (
        @Schema(description = "카테고리", example = "종이류,비닐류")
        String categories,
        @Schema(description = "태그", example = "종이백,비닐쇼핑백")
        String tags,
        @Schema(description = "배출 솔루션", example = "일반 종이 재질의 쇼핑백은 종이로 분리배출\r\n비닐 쇼핑백은 비닐류로 분리배출")
        String solution
){
    public Wiki toEntity(Account writer, Waste waste, Wiki original){
        return Wiki.builder()
                .writer(writer)
                .waste(waste)
                .original(original)
                .categories(categories)
                .tags(tags)
                .solution(solution)
                .build();
    }
}
