package com.stocksy.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StocksyApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(StocksyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StocksyApplication.class, args);
	}

	@Override
	public void run(String... args) {
		LOG.info("STOCKSY -> API - INICIALIZADO COM SUCESSO!");
	}
}
