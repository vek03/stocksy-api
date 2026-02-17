package com.stocksy.spring.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "COMPOSITION_ITEM")
public class CompositionItemEntity {
    @Id
    @Column(length = 36)
    public String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    public ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RAY_MATERIAL_ID", nullable = false)
    public RawMaterialEntity rawMaterial;

    @Column(name = "QUANTITY", nullable = false)
    public int quantity;

    public CompositionItemEntity() {
    }

    public CompositionItemEntity(String id, ProductEntity product, RawMaterialEntity rawMaterial, int quantity) {
        this.id = id;
        this.product = product;
        this.rawMaterial = rawMaterial;
        this.quantity = quantity;
    }
}