package com.bootcamp.exercicios.restaurante.dto;

import com.bootcamp.exercicios.restaurante.entity.Pedido;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PedidoDTO {
    private final Long id;
    private final Long idMesa;
    private final List<PratoDTO> pratos;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final BigDecimal valorTotal;

    public static Pedido converte(PedidoDTO pedido, Long id) {
        return new Pedido(pedido.getId(), id, PratoDTO.converteList(pedido.getPratos()), pedido.getValorTotal());
    }

    public static PedidoDTO converte(Pedido pedido) {
        return new PedidoDTO(pedido.getId(), pedido.getIdMesa(), PratoDTO.converte(pedido.getPratos()), pedido.getValorTotal());
    }

    public static List<PedidoDTO> converte(List<Pedido> pratos) {
        return pratos.stream().map(PedidoDTO::converte).collect(Collectors.toList());
    }
}
