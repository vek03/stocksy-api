package com.stocksy.spring.infrastructure.persistence.adapter;

import com.stocksy.domain.entities.Pagination;
import com.stocksy.domain.entities.RawMaterial;
import com.stocksy.spring.infrastructure.persistence.entities.ProductEntity;
import com.stocksy.spring.infrastructure.persistence.entities.RawMaterialEntity;
import org.springframework.data.domain.Page;

public class RawMaterialAdapter {
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

    public static Pagination<RawMaterial> cast(Page<RawMaterialEntity> page) {
        return new Pagination<>(
                page.getContent().stream().map(RawMaterialAdapter::cast).toList(),
                page.getPageable().getPageNumber(),
                page.getPageable().getPageSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}