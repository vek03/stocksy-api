package com.stocksy.spring.presentation.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import java.math.BigDecimal;
import java.util.List;

@Validated
public record ProductRequest(
        @NotBlank(message = "name is required")
        String name,

        @NotNull(message = "price is required")
        @Valid
        BigDecimal price,

        @NotNull(message = "composition is required")
        @Valid
        List<CompositionItemRequest> composition
) {
}
