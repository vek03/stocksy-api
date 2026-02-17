package com.stocksy.domain.entities;

public record Pagination<T> (
    Iterable<T> content,
    int pageNumber,
    int pageSize,
    long totalItems,
    int totalPages
) {}