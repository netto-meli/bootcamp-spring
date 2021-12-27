package com.bootcamp.exercicios.loja.repository;

import com.bootcamp.exercicios.loja.entity.Cliente;
import com.bootcamp.exercicios.loja.entity.Pedido;
import com.bootcamp.exercicios.loja.entity.Produto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteRepository {

    private List<Cliente> clientes = new ArrayList<>();

    public void set(Cliente cliente) {
        cliente.setId(clientes.size()+1);
        clientes.add(cliente);
    }

    public Cliente get(double id) {
        return clientes.stream().filter(c -> c.getId()  == id ).findAny().orElse(null);
    }

    public List<Cliente> list(){
        return clientes;
    }

    public Pedido addPedido(long idCliente, PedidoRepository listaPedido, List<Produto> listaProdutos) {
        Pedido pedido = new Pedido( listaPedido.newId(), listaProdutos );
        listaPedido.add(pedido);
        clientes.stream().filter((c -> c.getId() == idCliente)).findAny().orElse(null).adicionaPedido(pedido);
        return pedido;
    }
}