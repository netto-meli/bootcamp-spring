package com.bootcamp.exercicios.loja.repository;

import com.bootcamp.exercicios.loja.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    //@Query( value = "from pedido p where p.cliente_id = :id")
    List<Pedido> findAllByClienteId(@Param("id") Long id);
}
