package com.bootcamp.exercicios.restaurante.repository;

import com.bootcamp.exercicios.restaurante.entity.Mesa;
import com.bootcamp.exercicios.restaurante.entity.Pedido;
import com.bootcamp.exercicios.restaurante.entity.Prato;
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
    private List<Mesa> salaoDoRestaurante;
    private List<Prato> cardapioDoDia;

    public void abreRestaurante(int numeroMesas, List<Prato> pratos) {
        salaoDoRestaurante = new ArrayList<>();
        limpaMesas(numeroMesas);
        cardapioDoDia = pratos;
    }

    private void limpaMesas(int mesas){
        for (long i = 1; i <= mesas; i++) salaoDoRestaurante.add( new Mesa ( i, new ArrayList<>(), BigDecimal.ZERO));
    }

    public Mesa getId(Long idMesa) {
        return salaoDoRestaurante.stream()
                .filter(m -> m.getId().equals(idMesa))
                .findAny().orElse(null);
    }

    public Mesa adicionaPedidoMesa(Long idMesa, Pedido pedido) {
        return salaoDoRestaurante.stream().filter(m -> m.getId().equals(idMesa))
                .findAny().orElse(null).adicionaPedido(pedido);
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

    public List<Prato> getCardapio() {
        return cardapioDoDia;
    }

    public void verificaDisponibilidadePrato(Prato p) {
        Prato pratoDia = cardapioDoDia.stream().filter(dia -> dia.getId().equals(p.getId())).findFirst().orElse(null);
        if (pratoDia.getQuantidade() < p.getQuantidade()) throw new NullPointerException();
        //nullpointer intencional (no pratodo dia e na resposta)
    }

    public void baixaDePratos(List<Prato> pratosPedido) {
        for (Prato p : pratosPedido ) {
           Prato prato = cardapioDoDia.stream()
                    .filter(dia -> dia.getId().equals(p.getId()))
                    .findFirst().orElse(null);
            cardapioDoDia.stream()
                    .filter(dia -> dia.getId().equals(p.getId()))
                    .findFirst().orElse(null)
                    .setQuantidade( prato.getQuantidade() - p.getQuantidade() );
        }
    }

    public List<Prato> dadosPratosPedido(List<Prato> pratosPedido) {
        List<Prato> listaPratos = new ArrayList<>();
        for (Prato p : pratosPedido) {
            Prato pratoPedido = cardapioDoDia.stream()
                    .filter( pp -> pp.getId().equals(p.getId()))
                    .findFirst().orElse(null);
            Prato y = new Prato(pratoPedido.getId(),pratoPedido.getDescricao(), p.getQuantidade(), pratoPedido.getPreco() );
            listaPratos.add(y);
        }
        return listaPratos;
    }
}