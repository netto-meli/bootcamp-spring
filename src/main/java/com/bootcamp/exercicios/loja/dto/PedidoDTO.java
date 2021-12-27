package com.bootcamp.exercicios.loja.dto;

import com.bootcamp.exercicios.loja.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class PedidoDTO {
    private final long id;
    private final BigDecimal valorTotal;
    //private List<ProdutoDTO> pedidos;

    public static PedidoDTO converte(Pedido pedido) {
        return new PedidoDTO(pedido.getId(), pedido.getValorTotal());
    }
}
