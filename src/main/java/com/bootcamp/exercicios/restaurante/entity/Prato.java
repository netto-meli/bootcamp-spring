package com.bootcamp.exercicios.restaurante.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Prato {
    private final Long id;
    private final String descricao;
    private final int quantidade;
    private final BigDecimal preco;
}