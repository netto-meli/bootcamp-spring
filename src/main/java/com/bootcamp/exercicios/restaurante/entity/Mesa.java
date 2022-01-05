package com.bootcamp.exercicios.restaurante.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Mesa {
    private final Long id;
    private List<Pedido> pedidos;
    private BigDecimal valorTotal;

    public Mesa adicionaPedido(Pedido pedido){
        pedido.setId(pedidos.size()+1L);
        pedido.calculaValorTotal();
        pedidos.add(pedido);
        valorTotal = valorTotal.add(pedido.getValorTotal());
        return this;
    }

    public void fechaMesa(){
        this.pedidos = new ArrayList<>();
        this.valorTotal = BigDecimal.ZERO;
    }
}