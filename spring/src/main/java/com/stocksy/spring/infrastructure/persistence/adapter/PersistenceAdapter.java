package com.stocksy.spring.infrastructure.persistence.adapter;

import com.stocksy.domain.entities.CompositionItem;
import com.stocksy.domain.entities.Product;
import com.stocksy.domain.entities.RawMaterial;
import com.stocksy.spring.infrastructure.persistence.entities.CompositionItemEntity;
import com.stocksy.spring.infrastructure.persistence.entities.ProductEntity;
import com.stocksy.spring.infrastructure.persistence.entities.RawMaterialEntity;

import java.util.UUID;

public class PersistenceAdapter {
    public static ProductEntity cast(Product object) {
        return new ProductEntity(
                UUID.randomUUID().toString(),
                object.getName(),
                object.getPrice()
        );
    }

    public static Product cast(ProductEntity entity) {
        return new Product(
                entity.name,
                entity.price
        );
    }

    public static RawMaterialEntity cast(RawMaterial object) {
        return new RawMaterialEntity(
                object.id(),
                object.name(),
                object.stock_quantity()
        );
    }

    public static RawMaterial cast(RawMaterialEntity entity) {
        return new RawMaterial(
                entity.id,
                entity.name,
                entity.stock_quantity
        );
    }

    public static CompositionItemEntity cast(CompositionItem object) {
        return new CompositionItemEntity(
                object.id(),
                PersistenceAdapter.cast(object.product()),
                PersistenceAdapter.cast(object.rawMaterial()),
                object.quantity()
        );
    }

    public static CompositionItem cast(CompositionItemEntity entity) {
        return new CompositionItem(
                entity.id,
                PersistenceAdapter.cast(entity.product),
                PersistenceAdapter.cast(entity.rawMaterial),
                entity.quantity
        );
    }
}