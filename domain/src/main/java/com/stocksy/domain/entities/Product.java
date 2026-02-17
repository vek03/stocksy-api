package com.stocksy.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Product {
    private String id;
    private final String name;
    private final BigDecimal price;
    private final List<CompositionItem> composition = new ArrayList<>();

    public Product(String nome, BigDecimal price) {
        this.name = nome;
        this.price = price;
    }

    public void adicionarMateriaPrima(RawMaterial rawMaterial, int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Invalid quantity: " + quantity);

        composition.add(new CompositionItem(UUID.randomUUID().toString(), this, rawMaterial, quantity));
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public List<CompositionItem> getComposition() { return Collections.unmodifiableList(composition); }
}