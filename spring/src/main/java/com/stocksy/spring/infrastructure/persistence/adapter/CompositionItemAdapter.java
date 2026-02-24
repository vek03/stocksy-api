package com.stocksy.spring.infrastructure.persistence.adapter;

import com.stocksy.domain.entities.CompositionItem;
import com.stocksy.spring.infrastructure.persistence.entities.CompositionItemEntity;

public class CompositionItemAdapter {
    public static CompositionItemEntity cast(CompositionItem object) {
        return new CompositionItemEntity(
                object.id(),
                ProductAdapter.cast(object.product()),
                RawMaterialAdapter.cast(object.rawMaterial()),
                object.quantity()
        );
    }

    public static CompositionItem cast(CompositionItemEntity entity) {
        return new CompositionItem(
                entity.id,
                ProductAdapter.castWithoutComposition(entity.product),
                RawMaterialAdapter.cast(entity.rawMaterial),
                entity.quantity
        );
    }
}