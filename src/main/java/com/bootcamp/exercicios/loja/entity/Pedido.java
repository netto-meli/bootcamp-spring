package com.bootcamp.exercicios.loja.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valorTotal;
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public void calculaValorTotal(List<ItemPedido> itemPedidoList){
        BigDecimal valorPedido = BigDecimal.ZERO;
        for (ItemPedido ip : itemPedidoList) {
            valorPedido = valorPedido.add(ip.getValorTotal());
        }
        this.valorTotal = valorPedido;
    }
}
