package com.stocksy.spring.infrastructure.persistence.clients;

import com.stocksy.spring.infrastructure.persistence.entities.RawMaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialJpaRepositoryWithOracle extends JpaRepository<RawMaterialEntity, String>  {
}
