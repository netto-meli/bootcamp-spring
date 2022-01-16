package com.bootcamp.exercicios.loja.dto;

import com.bootcamp.exercicios.loja.entity.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ItemPedidoDTO {
    private Long id;
    private ProdutoDTO produtoDTO;
    private int quantidade;
    private BigDecimal valorTotal;

    public static ItemPedido converte(ItemPedidoDTO itemPedidoDTO, PedidoDTO pedido, ClienteDTO cliente) {
        return new ItemPedido(
                itemPedidoDTO.getId(),
                PedidoDTO.converte(pedido, cliente),
                ProdutoDTO.converte(itemPedidoDTO.getProdutoDTO()),
                itemPedidoDTO.getQuantidade(),
                itemPedidoDTO.getValorTotal()
        );
    }

    public static ItemPedidoDTO converte(ItemPedido itemPedido) {
        return new ItemPedidoDTO(
                itemPedido.getId(),
                ProdutoDTO.converte(itemPedido.getProduto()),
                itemPedido.getQuantidade(),
                itemPedido.getValorTotal()
        );
    }

    public static List<ItemPedidoDTO> converteList(List<ItemPedido> listaPedido) {
        List<ItemPedidoDTO> listaPedidoDTO = new ArrayList<>();
        listaPedido.forEach(pd -> listaPedidoDTO.add( converte(pd)));
        return listaPedidoDTO;
    }

    public static List<ItemPedido> converteList(List<ItemPedidoDTO> listaPedidoDTO, PedidoDTO pedidoDTO, ClienteDTO clienteDTO) {
        List<ItemPedido> listaPedido = new ArrayList<>();
        listaPedidoDTO.forEach(pd -> listaPedido.add( converte(pd, pedidoDTO,clienteDTO)));
        return listaPedido;
    }
}