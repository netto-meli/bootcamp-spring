package com.bootcamp.exercicios.loja.dto;

import com.bootcamp.exercicios.loja.entity.Cliente;
import com.bootcamp.exercicios.loja.entity.ItemPedido;
import com.bootcamp.exercicios.loja.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private long cpf;
    private String email;
    private long telefone;
    private List<PedidoDTO> pedidos;

    public static Cliente converte(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(),
                clienteDTO.getCpf(), clienteDTO.getEmail(), clienteDTO.getTelefone());
    }

    public static ClienteDTO converte(Cliente cliente, List<Pedido> listaPedidos, List<ItemPedido> listaItemPedido) {
        // todo dividir lista item pedido por pedidos
        List<PedidoDTO> pedidoDTOS = PedidoDTO.converteLista(listaPedidos, listaItemPedido);
        return new ClienteDTO(cliente.getId(), cliente.getNome(),
                cliente.getCpf(), cliente.getEmail(), cliente.getTelefone(), pedidoDTOS);
    }
}