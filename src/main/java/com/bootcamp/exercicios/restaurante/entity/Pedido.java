package com.bootcamp.exercicios.restaurante.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class Pedido {
    @Setter
    private Long id;
    private final Long idMesa;
    private final List<Prato> pratos;
    private BigDecimal valorTotal;

    public void calculaValorTotal() {
        BigDecimal valorPedido = BigDecimal.ZERO;
        for (Prato p : pratos) valorPedido = valorPedido.add(p.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade())));
        this.valorTotal = valorPedido;
    }
}
