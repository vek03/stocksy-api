package com.stocksy.spring.presentation.controller.dto.response;

import java.math.BigDecimal;

public record ProductCapacityResponse(
        String productId,
        String productName,
        BigDecimal maxQuantity
) {}
