package com.stocksy.spring.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "RAW_MATERIALS")
public class RawMaterialEntity {
    @Id
    @Column(length = 36)
    public String id;

    @Column(name = "NAME", nullable = false, length = 120)
    public String name;

    @Column(name = "STOCK_QUANTITY", nullable = false)
    public int stock_quantity;

    public RawMaterialEntity() {
    }

    public RawMaterialEntity(String id, String name, int stock_quantity) {
        this.id = id;
        this.name = name;
        this.stock_quantity = stock_quantity;
    }
}