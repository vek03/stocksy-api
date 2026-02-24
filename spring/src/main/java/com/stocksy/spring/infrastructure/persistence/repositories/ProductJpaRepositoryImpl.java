package com.stocksy.spring.infrastructure.persistence.repositories;

import com.stocksy.domain.entities.Pagination;
import com.stocksy.domain.entities.Product;
import com.stocksy.domain.exception.NotFoundException;
import com.stocksy.domain.repository.ProductRepository;
import com.stocksy.spring.infrastructure.persistence.adapter.ProductAdapter;
import com.stocksy.spring.infrastructure.persistence.clients.ProductJpaRepositoryWithOracle;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ProductJpaRepositoryImpl implements ProductRepository {
    private final ProductJpaRepositoryWithOracle repository;

    public ProductJpaRepositoryImpl(ProductJpaRepositoryWithOracle repository) {
        this.repository = repository;
    }

    @Override
    public Pagination<Product> getAll(int pageNumber, int pageSize) {
        return ProductAdapter.cast(repository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber)));
    }

    @Override
    public Product save(Product product) {
        repository.save(ProductAdapter.cast(product));

        return product;
    }

    @Override
    public Product update(Product product) {
        findById(product.getId());
        repository.save(ProductAdapter.cast(product));

        return product;
    }

    @Override
    public Product findById(String id) {
        return ProductAdapter.cast(repository.findByIdWithComposition(id).orElseThrow(() -> new NotFoundException("Product not found with id: " + id)));
    }

    @Override
    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }
}
