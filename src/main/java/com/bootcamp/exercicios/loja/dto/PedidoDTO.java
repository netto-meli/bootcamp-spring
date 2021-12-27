package com.bootcamp.exercicios.loja.dto;

import com.bootcamp.exercicios.loja.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PedidoDTO {
    private final long id;
    private final BigDecimal valorTotal;
    private List<ProdutoDTO> pedidos;

    public static PedidoDTO converte(Pedido pedido) {
        List<ProdutoDTO> listProdutoDTO = ProdutoDTO.converte(pedido.getProdutos());
        return new PedidoDTO(pedido.getId(), pedido.getValorTotal(), listProdutoDTO);
    }

    public static List<PedidoDTO> converte(List<Pedido> listaPedido) {
        List<PedidoDTO> listaPedidoDTO = new ArrayList<>();
        listaPedido.forEach(pd -> listaPedidoDTO.add( converte(pd)));
        return listaPedidoDTO;
    }
}
