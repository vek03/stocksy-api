package com.stocksy.domain.services;

import com.stocksy.domain.entities.CompositionItem;
import com.stocksy.domain.entities.Product;
import com.stocksy.domain.repository.ProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Map<Product, BigDecimal> calculateProductionCapacity() {

        List<Product> products = productRepository.findAllWithComposition();

        Map<Product, BigDecimal> result = new HashMap<>();

        for (Product product : products) {

            BigDecimal maxProducible = null;

            for (CompositionItem item : product.getComposition()) {

                BigDecimal stock = new BigDecimal(item.rawMaterial().stock_quantity());
                BigDecimal required = new BigDecimal(item.quantity());

                if (required.compareTo(BigDecimal.ZERO) == 0) continue;

                BigDecimal possible = stock.divide(required, 0, RoundingMode.DOWN);

                if (maxProducible == null || possible.compareTo(maxProducible) < 0) {
                    maxProducible = possible;
                }
            }

            result.put(product, maxProducible == null ? BigDecimal.ZERO : maxProducible);
        }

        return result;
    }
}
