package com.stocksy.spring.infrastructure.persistence.clients;

import com.stocksy.spring.infrastructure.persistence.entities.CompositionItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompositionItemJpaRepositoryWithOracle extends JpaRepository<CompositionItemEntity, String>  {
}
