package com.stocksy.domain.repository;

import com.stocksy.domain.entities.Pagination;
import com.stocksy.domain.entities.RawMaterial;

public interface RawMaterialRepository {
    Pagination<RawMaterial> getAll(final int pageNumber, final int pageSize);

    RawMaterial save(RawMaterial rawMaterial);

    RawMaterial update(RawMaterial rawMaterial);

    RawMaterial findById(final String id);

    void delete(final String id);
}
