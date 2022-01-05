package com.bootcamp.exercicios.restaurante.dto;

import com.bootcamp.exercicios.restaurante.entity.Prato;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PratoDTO {
    private final Long id;
    private final String descricao;
    private final int quantidade;
    private final BigDecimal preco;

    public static Prato converte(PratoDTO prato) {
        return new Prato(prato.getId(), prato.getDescricao(), prato.getQuantidade(), prato.getPreco());
    }

    public static PratoDTO converte(Prato prato) {
        return new PratoDTO(prato.getId(), prato.getDescricao(), prato.getQuantidade(), prato.getPreco());
    }

    public static List<PratoDTO> converte(List<Prato> pratos) {
        return pratos.stream().map(PratoDTO::converte).collect(Collectors.toList());
    }

    public static List<Prato> converteList(List<PratoDTO> pratos) {
        return pratos.stream().map(PratoDTO::converte).collect(Collectors.toList());
    }
}