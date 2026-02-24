package com.stocksy.spring.presentation.controller.dto.response;

public record CompositionItemResponse(
        String id,
        String name,
        int quantity
) {
}
