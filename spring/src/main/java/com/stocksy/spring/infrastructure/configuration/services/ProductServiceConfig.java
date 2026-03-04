package com.stocksy.spring.infrastructure.configuration.services;

import com.stocksy.domain.repository.ProductRepository;
import com.stocksy.domain.services.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceConfig {
    @Bean
    public ProductService productService(
            ProductRepository repository
    ) {
        return new ProductService(repository);
    }
}
