package com.stocksy.spring.presentation.controller;

import com.stocksy.domain.entities.Pagination;
import com.stocksy.domain.entities.Product;
import com.stocksy.domain.services.ProductService;
import com.stocksy.spring.application.usecases.CreateProductWithCompositionUseCase;
import com.stocksy.spring.infrastructure.persistence.repositories.ProductJpaRepositoryImpl;
import com.stocksy.spring.presentation.controller.adapter.ProductControllerAdapter;
import com.stocksy.spring.presentation.controller.dto.request.ProductRequest;
import com.stocksy.spring.presentation.controller.dto.response.ProductCapacityResponse;
import com.stocksy.spring.presentation.controller.dto.response.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stocksy/v1")
public class ProductController {
    private final ProductJpaRepositoryImpl repository;
    private final CreateProductWithCompositionUseCase createProductWithCompositionUseCase;
    private final ProductService productService;

    public ProductController(ProductJpaRepositoryImpl repository, CreateProductWithCompositionUseCase createProductWithCompositionUseCase, ProductService productService) {
        this.repository = repository;
        this.createProductWithCompositionUseCase = createProductWithCompositionUseCase;
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/product")
    public Pagination<ProductResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", defaultValue = "10") int pageSize
    ) {
        return ProductControllerAdapter.cast(repository.getAll(pageNumber, pageSize));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product")
    public ProductResponse register(@Valid @RequestBody ProductRequest request) {
        return ProductControllerAdapter.cast(createProductWithCompositionUseCase.execute(request));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/product/{id}")
    public ProductResponse update(
            @PathVariable("id") String id,
            @Valid @RequestBody ProductRequest request
    ) {
        Product product = ProductControllerAdapter.cast(request, id);
        return ProductControllerAdapter.cast(repository.update(product));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/product/{id}")
    @Cacheable(value = "product-id-cache", key = "#id")
    public Product getById(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable("id") String id) {
        repository.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/product/production-capacity")
    public List<ProductCapacityResponse> calculateProductionCapacity() {
        Map<Product, BigDecimal> capacity = productService.calculateProductionCapacity();

        return capacity.entrySet()
                .stream()
                .map(entry -> new ProductCapacityResponse(
                        entry.getKey().getId(),
                        entry.getKey().getName(),
                        entry.getValue()
                ))
                .toList();
    }
}