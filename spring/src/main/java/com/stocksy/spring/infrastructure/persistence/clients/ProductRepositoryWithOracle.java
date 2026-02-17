package com.stocksy.spring.infrastructure.persistence.clients;

import com.stocksy.spring.infrastructure.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryWithOracle extends JpaRepository<ProductEntity, String>  {
}
