package com.stocksy.spring;

import com.stocksy.domain.entities.Product;
import com.stocksy.spring.infrastructure.persistence.repositories.ProductRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;

@SpringBootApplication
@EnableCaching
@EntityScan("com.stocksy")
@EnableJpaRepositories("com.stocksy")
public class StocksyApplication implements CommandLineRunner {
	private ProductRepositoryImpl productRepository;
	private static final Logger LOG = LoggerFactory.getLogger(StocksyApplication.class);

	public StocksyApplication(ProductRepositoryImpl productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(StocksyApplication.class, args);
	}

	@Override
	public void run(String... args) {
		productRepository.save(new Product(
				"Produto 1",
				BigDecimal.TEN
		));
		LOG.info("STOCKSY -> API - INICIALIZADO COM SUCESSO!");
	}
}
