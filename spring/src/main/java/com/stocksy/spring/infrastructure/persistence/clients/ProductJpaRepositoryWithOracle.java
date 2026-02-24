package com.stocksy.spring.infrastructure.persistence.clients;

import com.stocksy.spring.infrastructure.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductJpaRepositoryWithOracle extends JpaRepository<ProductEntity, String>  {
    @Query("""
        SELECT DISTINCT p FROM ProductEntity p
        LEFT JOIN FETCH p.composition i
        LEFT JOIN FETCH i.rawMaterial
        WHERE p.id = :id
    """)
    Optional<ProductEntity> findByIdWithComposition(@Param("id") String id);
}
