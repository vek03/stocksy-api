package com.stocksy.spring.infrastructure.persistence.repositories;

import com.stocksy.domain.entities.Pagination;
import com.stocksy.domain.entities.Product;
import com.stocksy.spring.infrastructure.persistence.adapter.PersistenceAdapter;
import com.stocksy.spring.infrastructure.persistence.clients.ProductRepositoryWithOracle;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements com.stocksy.domain.repository.ProductRepository {
    private final ProductRepositoryWithOracle repository;

    public ProductRepositoryImpl(ProductRepositoryWithOracle repository) {
        this.repository = repository;
    }

    @Override
    public Pagination<Product> getAll(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Product save(Product product) {
        repository.save(PersistenceAdapter.cast(product));

        return product;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Product findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
