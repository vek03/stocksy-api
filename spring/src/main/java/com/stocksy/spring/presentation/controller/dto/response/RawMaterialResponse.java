package com.stocksy.spring.presentation.controller.dto.response;

public record RawMaterialResponse(
        String id,
        String name,
        int stock_quantity
) {
}
