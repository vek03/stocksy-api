package com.stocksy.domain.entities;

import java.math.BigDecimal;

public record RawMaterial (
    Long id,
    String name,
    BigDecimal stock_quantity
) {}