package com.example.demo.bounded_context.location.dto;

public record LocationResponse(
        String address,
        Double latitude,
        Double longitude
) {
    public static LocationResponse of(String address, Double latitude, Double longitude) {
        return new LocationResponse(address, latitude, longitude);
    }
}
