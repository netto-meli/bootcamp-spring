package com.bootcamp.exercicios.loja.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Produto produto;
    private int quantidade;
    private BigDecimal valorTotal;

    public void calculaValorTotal(){
        this.valorTotal = produto.getPreco()
                .multiply(
                        BigDecimal.valueOf(this.quantidade)
                );
    }

}