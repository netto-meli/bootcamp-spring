package com.bootcamp.exercicios.restaurante.repository;

import com.bootcamp.exercicios.restaurante.entity.Mesa;
import com.bootcamp.exercicios.restaurante.entity.Pedido;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RestauranteRepository {

    @Getter
    private BigDecimal valorCaixa = BigDecimal.ZERO;
    private final List<Mesa> salaoDoRestaurante = new ArrayList<>();

    //public void abreRestaurante(int numeroMesas, List<Prato> pratos){
    public void abreRestaurante(){
        for (long i = 1; i <= 5; i++) salaoDoRestaurante.add( new Mesa ( i, new ArrayList<>(), BigDecimal.ZERO));
        //carregar pratos tb
    }

    public Mesa getId(Long idMesa) {
        return salaoDoRestaurante.stream()
                .filter(m -> m.getId().equals(idMesa))
                .findAny().orElse(null);
    }

    public Mesa adicionaPedidoMesa(Long idMesa, Pedido pedido) {
        return Objects.requireNonNull(salaoDoRestaurante.stream().filter(m -> m.getId().equals(idMesa))
                .findAny().orElse(null)).adicionaPedido(pedido);
    }

    public List<Mesa> verificaMesasLivres() {
        return salaoDoRestaurante.stream()
                .filter(mesa -> mesa.getValorTotal().equals(BigDecimal.ZERO))
                .collect(Collectors.toList());
    }

    public BigDecimal fecharMesa(Long idMesa){
        BigDecimal valorPago = getId(idMesa).getValorTotal();
        adicionaValorEmCaixa(valorPago);
        Objects.requireNonNull(salaoDoRestaurante.stream().filter(m -> m.getId().equals(idMesa))
                .findAny().orElse(null)).fechaMesa();
        return valorPago;
    }

    private void adicionaValorEmCaixa(BigDecimal pagamento){
        valorCaixa = valorCaixa.add(pagamento);
    }
}