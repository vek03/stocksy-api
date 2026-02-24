package com.stocksy.spring.presentation.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record RawMaterialRequest(
        @NotBlank(message = "name is required")
        String name,

        @NotNull(message = "stock quantity is required")
        int stock_quantity
) {
}
