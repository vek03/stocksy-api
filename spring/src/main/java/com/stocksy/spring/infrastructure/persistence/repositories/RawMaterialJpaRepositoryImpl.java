package com.stocksy.spring.infrastructure.persistence.repositories;

import com.stocksy.domain.entities.Pagination;
import com.stocksy.domain.entities.RawMaterial;
import com.stocksy.domain.exception.NotFoundException;
import com.stocksy.spring.infrastructure.persistence.adapter.RawMaterialAdapter;
import com.stocksy.spring.infrastructure.persistence.clients.RawMaterialJpaRepositoryWithOracle;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class RawMaterialJpaRepositoryImpl implements com.stocksy.domain.repository.RawMaterialRepository {
    private final RawMaterialJpaRepositoryWithOracle repository;

    public RawMaterialJpaRepositoryImpl(RawMaterialJpaRepositoryWithOracle repository) {
        this.repository = repository;
    }

    @Override
    public Pagination<RawMaterial> getAll(int pageNumber, int pageSize) {
        return RawMaterialAdapter.cast(repository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber)));
    }

    @Override
    public RawMaterial save(RawMaterial rawMaterial) {
        repository.save(RawMaterialAdapter.cast(rawMaterial));

        return rawMaterial;
    }

    @Override
    public RawMaterial update(RawMaterial rawMaterial) {
        repository.save(RawMaterialAdapter.cast(rawMaterial));

        return rawMaterial;
    }

    @Override
    public RawMaterial findById(String id) {
        return RawMaterialAdapter.cast(repository.findById(id).orElseThrow(() -> new NotFoundException("Raw Material not found with id: " + id)));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
