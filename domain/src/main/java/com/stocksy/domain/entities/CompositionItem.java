package com.stocksy.domain.entities;

public record CompositionItem (
    String id,
    Product product,
    RawMaterial rawMaterial,
    int quantity
) {}