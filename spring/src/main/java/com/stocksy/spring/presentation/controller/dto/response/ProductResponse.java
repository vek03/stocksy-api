package com.stocksy.spring.presentation.controller.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        String id,
        String name,
        BigDecimal price,
        List<CompositionItemResponse> composition
) {
}
