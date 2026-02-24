package com.stocksy.spring.presentation.controller;

import com.stocksy.domain.entities.Pagination;
import com.stocksy.domain.entities.RawMaterial;
import com.stocksy.spring.infrastructure.persistence.repositories.RawMaterialJpaRepositoryImpl;
import com.stocksy.spring.presentation.controller.adapter.RawMaterialControllerAdapter;
import com.stocksy.spring.presentation.controller.dto.request.RawMaterialRequest;
import com.stocksy.spring.presentation.controller.dto.response.RawMaterialResponse;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocksy/v1")
public class RawMaterialController {
    private final RawMaterialJpaRepositoryImpl repository;

    public RawMaterialController(RawMaterialJpaRepositoryImpl repository) {
        this.repository = repository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rawMaterial")
    public Pagination<RawMaterialResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", defaultValue = "10") int pageSize
    ) {
        return RawMaterialControllerAdapter.cast(repository.getAll(pageNumber, pageSize));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/rawMaterial")
    public RawMaterialResponse register(@Valid @RequestBody RawMaterialRequest request) {
        RawMaterial rawMaterial = RawMaterialControllerAdapter.cast(request);
        return RawMaterialControllerAdapter.cast(repository.save(rawMaterial));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/rawMaterial/{id}")
    public RawMaterialResponse update(
            @PathVariable("id") String id,
            @Valid @RequestBody RawMaterialRequest request
    ) {
        RawMaterial rawMaterial = RawMaterialControllerAdapter.cast(request, id);
        return RawMaterialControllerAdapter.cast(repository.update(rawMaterial));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rawMaterial/{id}")
    @Cacheable(value = "rawMaterial-id-cache", key = "#id")
    public RawMaterialResponse getById(@PathVariable("id") String id) {
        return RawMaterialControllerAdapter.cast(repository.findById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/rawMaterial/{id}")
    public void delete(@PathVariable("id") String id) {
        repository.delete(id);
    }
}