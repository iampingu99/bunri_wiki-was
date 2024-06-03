package com.example.demo.bounded_context.solution.dto;

import lombok.NonNull;

public record KeywordRequest(
        @NonNull String keyword
) {
}
