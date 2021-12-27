package com.bootcamp.exercicios.loja.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Pedido {
    private final long id;
    private final BigDecimal valorTotal;
    private final List<Produto> produtos;

    public Pedido(long idPedido, List<Produto> listaProdutos) {
        this.id = idPedido;
        this.produtos = listaProdutos;
        BigDecimal valorPedido = BigDecimal.ZERO;
        for (Produto p : listaProdutos) valorPedido = valorPedido.add(p.getPreco());
        this.valorTotal = valorPedido;
    }
}
