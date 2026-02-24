package com.stocksy.spring.infrastructure.persistence.adapter;

import com.stocksy.domain.entities.Pagination;
import com.stocksy.domain.entities.Product;
import com.stocksy.spring.infrastructure.persistence.entities.ProductEntity;
import com.stocksy.spring.presentation.controller.dto.request.ProductRequest;
import org.springframework.data.domain.Page;
import java.util.UUID;

public class ProductAdapter {
    public static Product cast(ProductRequest object) {
        return new Product(
                UUID.randomUUID().toString(),
                object.name(),
                object.price()
        );
    }

    public static ProductEntity cast(Product object) {
        return new ProductEntity(
                object.getId(),
                object.getName(),
                object.getPrice(),
                object.getComposition() != null && !object.getComposition().isEmpty() ? object.getComposition().stream().map(CompositionItemAdapter::cast).toList() : null
        );
    }

    public static Product cast(ProductEntity entity) {
        return new Product(
                entity.id,
                entity.name,
                entity.price,
                entity.composition.stream().map(CompositionItemAdapter::cast).toList()
        );
    }

    public static Product castWithoutComposition(ProductEntity entity) {
        return new Product(
                entity.id,
                entity.name,
                entity.price
        );
    }

    public static Pagination<Product> cast(Page<ProductEntity> page) {
        return new Pagination<>(
                page.getContent().stream().map(ProductAdapter::castWithoutComposition).toList(),
                page.getPageable().getPageNumber(),
                page.getPageable().getPageSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}