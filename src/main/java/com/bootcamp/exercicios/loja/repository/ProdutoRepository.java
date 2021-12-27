package com.bootcamp.exercicios.loja.repository;

import com.bootcamp.exercicios.loja.entity.Produto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProdutoRepository {

    private List<Produto> produtos = new ArrayList<>();

    public void add(List<Produto> prods) {
        produtos.addAll(prods);
    }

    public Produto get(double id) {
        Optional<Produto> optional = produtos.stream().filter(c -> c.getId() == id).findAny();
        return optional.orElse(null);
    }
}