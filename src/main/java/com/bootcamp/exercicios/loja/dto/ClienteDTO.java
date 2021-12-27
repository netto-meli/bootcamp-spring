package com.bootcamp.exercicios.loja.dto;

import com.bootcamp.exercicios.loja.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ClienteDTO {
    private final long id;
    private final String nome;
    private final long cpf;
    private final String email;
    private final long telefone;
    // private List<PedidoDTO> pedidos;

    public static Cliente converte(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(),
                clienteDTO.getCpf(), clienteDTO.getEmail(), clienteDTO.getTelefone(), new ArrayList<>());
    }

    public static ClienteDTO converte(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNome(),
                cliente.getCpf(), cliente.getEmail(), cliente.getTelefone());
    }
}