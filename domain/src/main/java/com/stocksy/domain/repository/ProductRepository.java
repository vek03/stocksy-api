package com.stocksy.domain.repository;

import com.stocksy.domain.entities.Pagination;
import com.stocksy.domain.entities.Product;

import java.util.List;

public interface ProductRepository {
    Pagination<Product> getAll(final int pageNumber, final int pageSize);

    List<Product> findAllWithComposition();

    Product save(Product product);

    Product update(Product product);

    Product findById(final String id);

    void delete(final String id);
}
