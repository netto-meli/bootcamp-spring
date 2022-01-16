package com.bootcamp.exercicios.loja.repository;

import com.bootcamp.exercicios.loja.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}