package com.stocksy.spring.infrastructure.persistence.clients;

import com.stocksy.spring.infrastructure.persistence.entities.CompositionItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompositionItemRepositoryWithOracle extends JpaRepository<CompositionItemEntity, String>  {
}
