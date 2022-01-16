package com.bootcamp.exercicios.loja.repository;

import com.bootcamp.exercicios.loja.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}