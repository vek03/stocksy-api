package com.stocksy.spring.presentation.controller.adapter;

import com.stocksy.domain.entities.Pagination;
import com.stocksy.domain.entities.RawMaterial;
import com.stocksy.spring.presentation.controller.dto.request.RawMaterialRequest;
import com.stocksy.spring.presentation.controller.dto.response.RawMaterialResponse;

import java.util.List;
import java.util.UUID;

public class RawMaterialControllerAdapter {
    private RawMaterialControllerAdapter() {
    }

    public static RawMaterialResponse cast(RawMaterial rawMaterial) {
        return new RawMaterialResponse(
                rawMaterial.id(),
                rawMaterial.name(),
                rawMaterial.stock_quantity()
        );
    }

    public static RawMaterial cast(RawMaterialRequest request) {
        return new RawMaterial(
                UUID.randomUUID().toString(),
                request.name(),
                request.stock_quantity()
        );
    }

    public static RawMaterial cast(RawMaterialRequest request, String id) {
        return new RawMaterial(
                id,
                request.name(),
                request.stock_quantity()
        );
    }

    public static Pagination<RawMaterialResponse> cast(Pagination<RawMaterial> pagination) {
        List<RawMaterial> rawMaterials = (List<RawMaterial>) pagination.content();

        return new Pagination<>(
                rawMaterials.stream().map(RawMaterialControllerAdapter::cast).toList(),
                pagination.pageNumber(),
                pagination.pageSize(),
                pagination.totalItems(),
                pagination.totalPages()
        );
    }
}
