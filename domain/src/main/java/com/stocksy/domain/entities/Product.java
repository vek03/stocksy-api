package com.stocksy.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Product {
    private final String id;
    private final String name;
    private final BigDecimal price;
    private final List<CompositionItem> composition = new ArrayList<>();

    public Product(String id, String nome, BigDecimal price) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("Invalid id: " + id);

        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Invalid name: " + nome);

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Invalid price: " + price);

        this.id = id;
        this.name = nome;
        this.price = price;
    }

    public Product(String id, String nome, BigDecimal price, List<CompositionItem> composition) {
        this(id, nome, price);

        if (composition == null || composition.isEmpty())
            throw new IllegalArgumentException("Invalid composition: " + composition);

        this.composition.addAll(composition);
    }

    public void addCompositionItem(RawMaterial rawMaterial, int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Invalid quantity: " + quantity);

        composition.add(new CompositionItem(UUID.randomUUID().toString(), getCopyWithoutComposition(), rawMaterial, quantity));
    }

    public boolean rawMaterialExistsInComposition(String rawMaterialId) {
        return composition.stream().anyMatch(item -> item.rawMaterial().id().equals(rawMaterialId));
    }

    private Product getCopyWithoutComposition() {
        return new Product(id, name, price);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public List<CompositionItem> getComposition() { return Collections.unmodifiableList(composition); }
}