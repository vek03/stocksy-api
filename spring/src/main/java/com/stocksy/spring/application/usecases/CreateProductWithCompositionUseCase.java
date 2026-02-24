package com.stocksy.spring.application.usecases;

import com.stocksy.domain.entities.Product;
import com.stocksy.domain.entities.RawMaterial;
import com.stocksy.domain.repository.ProductRepository;
import com.stocksy.domain.repository.RawMaterialRepository;
import com.stocksy.spring.infrastructure.persistence.adapter.ProductAdapter;
import com.stocksy.spring.presentation.controller.dto.request.CompositionItemRequest;
import com.stocksy.spring.presentation.controller.dto.request.ProductRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateProductWithCompositionUseCase {
    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public CreateProductWithCompositionUseCase(
            ProductRepository produtoRepository,
            RawMaterialRepository materiaPrimaRepository
    ) {
        this.productRepository = produtoRepository;
        this.rawMaterialRepository = materiaPrimaRepository;
    }

    @Transactional
    public Product execute(ProductRequest request) {
        Product product = ProductAdapter.cast(request);

        for (CompositionItemRequest itemInput : request.composition()) {
            if(itemInput.quantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }

            if(product.rawMaterialExistsInComposition(itemInput.rawMaterialId())) {
                //throw new IllegalArgumentException("Raw material already exists in composition");

                //next iteration of the loop, skipping the current item
                continue;
            }

            RawMaterial rawMaterial = rawMaterialRepository.findById(itemInput.rawMaterialId());

            product.addCompositionItem(
                    rawMaterial,
                    itemInput.quantity()
            );
        }

        return productRepository.save(product);
    }
}
