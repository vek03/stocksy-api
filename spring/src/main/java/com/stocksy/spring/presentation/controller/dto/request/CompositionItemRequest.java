package com.stocksy.spring.presentation.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public record CompositionItemRequest(
        @NotBlank(message = "raw material id is required")
        String rawMaterialId,

        @NotNull(message = "quantity is required")
        int quantity
) {
}
