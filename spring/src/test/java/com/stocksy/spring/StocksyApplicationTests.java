package com.stocksy.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = StocksyApplication.class)
@ActiveProfiles("test")
class StocksyApplicationTests {
	@Test
	void contextLoads() {	}
}