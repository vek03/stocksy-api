package com.stocksy.spring.presentation.controller;

import com.stocksy.domain.entities.Pagination;
import com.stocksy.domain.entities.Product;
import com.stocksy.spring.application.usecases.CreateProductWithCompositionUseCase;
import com.stocksy.spring.infrastructure.persistence.repositories.ProductJpaRepositoryImpl;
import com.stocksy.spring.presentation.controller.adapter.ProductControllerAdapter;
import com.stocksy.spring.presentation.controller.dto.request.ProductRequest;
import com.stocksy.spring.presentation.controller.dto.response.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocksy/v1")
public class ProductController {
    private final ProductJpaRepositoryImpl repository;
    private final CreateProductWithCompositionUseCase createProductWithCompositionUseCase;

    public ProductController(ProductJpaRepositoryImpl repository, CreateProductWithCompositionUseCase createProductWithCompositionUseCase) {
        this.repository = repository;
        this.createProductWithCompositionUseCase = createProductWithCompositionUseCase;
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
}