package com.bootcamp.exercicios.loja.dto;

import com.bootcamp.exercicios.loja.entity.Cliente;
import com.bootcamp.exercicios.loja.entity.ItemPedido;
import com.bootcamp.exercicios.loja.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private BigDecimal valorTotal;
    private List<ItemPedidoDTO> itemPedidoList;

    public static PedidoDTO converte(Pedido pedido, List<ItemPedido> listaItemPedido) {
        List<ItemPedidoDTO> listProdutoDTO = ItemPedidoDTO.converteList(listaItemPedido);
        return new PedidoDTO(pedido.getId(), pedido.getValorTotal(), listProdutoDTO);
    }

    public static Pedido converte(PedidoDTO pedido, ClienteDTO cliente) {
        return new Pedido(pedido.getId(), pedido.getValorTotal(), ClienteDTO.converte(cliente) );
    }

    public static List<PedidoDTO> converteLista(List<Pedido> listaPedido, List<ItemPedido> listaItemPedido) {
        List<PedidoDTO> listaPedidoDTO = new ArrayList<>();
        listaPedido.forEach(pd -> listaPedidoDTO.add( converte(pd, listaItemPedido)));
        return listaPedidoDTO;
    }
}
