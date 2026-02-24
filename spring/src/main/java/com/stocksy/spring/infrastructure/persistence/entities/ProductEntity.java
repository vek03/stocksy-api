package com.stocksy.spring.infrastructure.persistence.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
public class ProductEntity {
    @Id
    @Column(length = 36)
    public String id;

    @Column(name = "NAME")
    public String name;

    @Column(name = "PRICE")
    public BigDecimal price;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    public List<CompositionItemEntity> composition = new ArrayList<>();

    public ProductEntity() {
    }

    public ProductEntity(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductEntity(String id, String name, BigDecimal price, List<CompositionItemEntity> composition) {
        this(id, name, price);
        this.composition = composition;
    }
}