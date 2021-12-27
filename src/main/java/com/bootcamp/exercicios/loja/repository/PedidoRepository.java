package com.bootcamp.exercicios.loja.repository;

import com.bootcamp.exercicios.loja.entity.Pedido;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PedidoRepository {

    private List<Pedido> pedidos = new ArrayList<>();

    public long newId() {
        return pedidos.size() + 1;
    }

    public void add(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido get(long idPedido) {
        Optional<Pedido> op = pedidos.stream().filter(p -> p.getId() == idPedido).findAny();
        return op.orElse(null);
    }
}
