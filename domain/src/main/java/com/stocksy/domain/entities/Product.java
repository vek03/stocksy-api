package com.stocksy.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product {
    private Long id;
    private final String name;
    private final List<CompositionItem> composicao = new ArrayList<>();

    public Product(String nome) {
        this.name = nome;
    }

    public void adicionarMateriaPrima(RawMaterial rawMaterial, BigDecimal quantidade) {
        if (quantidade.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Invalid quantity: " + quantidade);

        composicao.add(new CompositionItem(this, rawMaterial, quantidade));
    }

    public String getNome() { return name; }
    public List<CompositionItem> getComposicao() { return Collections.unmodifiableList(composicao); }
}