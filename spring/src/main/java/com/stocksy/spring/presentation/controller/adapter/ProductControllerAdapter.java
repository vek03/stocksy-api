package com.stocksy.spring.presentation.controller.adapter;

import com.stocksy.domain.entities.CompositionItem;
import com.stocksy.domain.entities.Pagination;
import com.stocksy.domain.entities.Product;
import com.stocksy.domain.entities.RawMaterial;
import com.stocksy.spring.presentation.controller.dto.request.ProductRequest;
import com.stocksy.spring.presentation.controller.dto.response.CompositionItemResponse;
import com.stocksy.spring.presentation.controller.dto.response.ProductResponse;

import java.util.List;
import java.util.UUID;

public class ProductControllerAdapter {
    private ProductControllerAdapter() {
    }

    public static ProductResponse cast(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getComposition().stream().map(ProductControllerAdapter::cast).toList()
        );
    }

    public static CompositionItemResponse cast(CompositionItem compositionItem) {
        return new CompositionItemResponse(
                compositionItem.id(),
                compositionItem.rawMaterial().name(),
                compositionItem.quantity()
        );
    }

    public static Product cast(ProductRequest request) {
        return new Product(
                UUID.randomUUID().toString(),
                request.name(),
                request.price()
        );
    }

    public static Product cast(ProductRequest request, String id) {
        return new Product(
                id,
                request.name(),
                request.price()
        );
    }

    public static Pagination<ProductResponse> cast(Pagination<Product> pagination) {
        List<Product> products = (List<Product>) pagination.content();

        return new Pagination<>(
                products.stream().map(ProductControllerAdapter::cast).toList(),
                pagination.pageNumber(),
                pagination.pageSize(),
                pagination.totalItems(),
                pagination.totalPages()
        );
    }
}
