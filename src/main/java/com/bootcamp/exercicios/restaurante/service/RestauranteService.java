package com.bootcamp.exercicios.restaurante.service;

import com.bootcamp.exercicios.restaurante.entity.Mesa;
import com.bootcamp.exercicios.restaurante.entity.Pedido;
import com.bootcamp.exercicios.restaurante.entity.Prato;
import com.bootcamp.exercicios.restaurante.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    RestauranteRepository restaurante;

    public Mesa getPedidosMesa(Long idMesa) {
        return restaurante.getId(idMesa);
    }

    public Mesa adicionaPedidoMesa(Long idMesa, Pedido pedido) {
        List<Prato> pratosPedido = pedido.getPratos();
        for (Prato p: pratosPedido) {
            restaurante.verificaDisponibilidadePrato(p);
        }
        restaurante.baixaDePratos(pratosPedido);
        pedido.setPratos(restaurante.dadosPratosPedido(pratosPedido));
        return restaurante.adicionaPedidoMesa(idMesa,pedido);
    }

    public BigDecimal fecharMesa(Long idMesa){
        return restaurante.fecharMesa(idMesa);
    }

    public List<Long> verificaMesasLivres() {
        List<Long> listaMesasId = new ArrayList<>();
        List<Mesa> mesas = restaurante.verificaMesasLivres();
        for (Mesa m : mesas) listaMesasId.add(m.getId());
        return listaMesasId;
    }

    public BigDecimal getValorCaixa(){
        return restaurante.getValorCaixa();
    }

    public void abreRestaurante(int numeroMesas, List<Prato> listaPratos) {
        restaurante.abreRestaurante(numeroMesas, listaPratos);
    }

    public List<Prato> getCardapio() {
        List<Prato> listaPratos = restaurante.getCardapio();
        return listaPratos;
    }
}