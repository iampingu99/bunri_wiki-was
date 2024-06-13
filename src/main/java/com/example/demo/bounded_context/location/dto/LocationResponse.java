package com.example.demo.bounded_context.location.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LocationResponse", description = "수거함 위치 목록 조회 DTO")
public record LocationResponse(
        @Schema(description = "주소", example = "대구 달서구 야외음악당로 38")
        String address,
        @Schema(description = "위도", example = "35.84061406")
        Double latitude,
        @Schema(description = "경도", example = "128.5531486")
        Double longitude
) {
    public static LocationResponse of(String address, Double latitude, Double longitude) {
        return new LocationResponse(address, latitude, longitude);
    }
}
