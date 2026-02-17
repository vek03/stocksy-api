package com.stocksy.domain.entities;

import java.math.BigDecimal;

public record CompositionItem (
    Product product,
    RawMaterial rawMaterial,
    BigDecimal quantity
) {}