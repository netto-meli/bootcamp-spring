package com.bootcamp.exercicios.loja.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Produto {
    private final long id;
    private final String descricao;
    private final String cor;
    private final long quantidade;
    private final BigDecimal preco;
}