package com.bootcamp.exercicios.loja.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Cliente {
    private long id;
    private final String nome;
    private final long cpf;
    private final String email;
    private final long telefone;
    private List<Pedido> pedidos;

    public void adicionaPedido(Pedido pedido){
        pedidos.add(pedido);
    }
}