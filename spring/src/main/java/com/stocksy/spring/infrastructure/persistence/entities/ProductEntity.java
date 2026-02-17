package com.stocksy.spring.infrastructure.persistence.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
public class ProductEntity {
    @Id
    @Column(length = 36)
    public String id;

    @Column(name = "NAME")
    public String name;

    @Column(name = "PRICE")
    public BigDecimal price;

    public ProductEntity() {
    }

    public ProductEntity(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}