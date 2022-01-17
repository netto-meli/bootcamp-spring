package com.bootcamp.exercicios.loja.repository;

import com.bootcamp.exercicios.loja.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    @Query( " from ItemPedido ip " +
            " join Pedido p on p.cliente.id = :id " +
            " and p.id = ip.pedido.id " )
    List<ItemPedido> findAllByClienteId(@Param("id") Long id);

    //@Query( value = " from item_pedido ip where ip.pedido_id = :id " )
    @Query("select i from ItemPedido i where i.pedido.id = :id")
    List<ItemPedido> findAllByPedidoId(@Param("id") Long id);
}