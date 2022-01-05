package com.bootcamp.exercicios.restaurante.dto;

import com.bootcamp.exercicios.restaurante.entity.Mesa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class MesaDTO {
    private final Long id;
    private List<PedidoDTO> listaPedidos;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal valorTotal;

    public static MesaDTO converte(Mesa mesa) {
        return new MesaDTO(mesa.getId(), PedidoDTO.converte(mesa.getPedidos()), mesa.getValorTotal());
    }
}