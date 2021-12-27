package com.bootcamp.exercicios.loja.dto;

import com.bootcamp.exercicios.loja.entity.Pedido;
import com.bootcamp.exercicios.loja.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProdutoDTO {
    private final long id;
    private final String descricao;
    private final String cor;
    private final long quantidade;
    private final BigDecimal preco;

    public static Produto converte(ProdutoDTO produtoDTO) {
        return new Produto(produtoDTO.getId(), produtoDTO.getDescricao(), produtoDTO.getCor(),
                produtoDTO.getQuantidade(),produtoDTO.getPreco());
    }

    public static ProdutoDTO converte(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getDescricao(), produto.getCor(),
                produto.getQuantidade(),produto.getPreco());
    }

}