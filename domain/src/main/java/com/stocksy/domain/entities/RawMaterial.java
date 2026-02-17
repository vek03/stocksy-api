package com.stocksy.domain.entities;

public record RawMaterial (
    String id,
    String name,
    int stock_quantity
) {}