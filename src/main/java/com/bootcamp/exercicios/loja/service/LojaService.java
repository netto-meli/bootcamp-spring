package com.bootcamp.exercicios.loja.service;

import com.bootcamp.exercicios.loja.entity.Cliente;
import com.bootcamp.exercicios.loja.entity.ItemPedido;
import com.bootcamp.exercicios.loja.entity.Pedido;
import com.bootcamp.exercicios.loja.entity.Produto;
import com.bootcamp.exercicios.loja.repository.ClienteRepository;
import com.bootcamp.exercicios.loja.repository.ItemPedidoRepository;
import com.bootcamp.exercicios.loja.repository.PedidoRepository;
import com.bootcamp.exercicios.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojaService {

	@Autowired private ClienteRepository clienteRepository;
	@Autowired private ProdutoRepository produtoRepository;
	@Autowired private PedidoRepository pedidoRepository;
	@Autowired private ItemPedidoRepository itemPedidoRepository;

	public Cliente adicionaCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public List<Produto> adicionaProdutos(List<Produto> produtos) {
		return produtoRepository.saveAll(produtos);
	}

	public Pedido adicionaPedido(String idCliente, List<ItemPedido> listaProdutosPedido) {
		for ( ItemPedido ip : listaProdutosPedido ) {
			ip.setProduto(produtoRepository.getById(ip.getProduto().getId()));
			ip.calculaValorTotal();
		}
		Pedido pedido = new Pedido();
		pedido.calculaValorTotal(listaProdutosPedido);
		pedido.setCliente( new Cliente(Long.valueOf(idCliente), null, 0, null, 0));
		pedido = pedidoRepository.save(pedido);
		Pedido p = pedido;
		listaProdutosPedido.forEach( lp -> lp.setPedido( p ) );
		itemPedidoRepository.saveAll(listaProdutosPedido);
		return pedido;
	}

	public List<Pedido> consultarPedidos(String idCliente) {
		return pedidoRepository.findAllByClienteId(Long.valueOf(idCliente));
	}
/*
	public List<ItemPedido> consultarItemPedidos(String idCliente) {
		return itemPedidoRepository.findAllByClienteId(Long.valueOf(idCliente));
	}
*/
	public List<ItemPedido> consultarItemPedido(String idPedido) {
		return itemPedidoRepository.findAllByPedidoId(Long.valueOf(idPedido));
	}

	public Pedido consultarPedido(String idPedido) {
		return pedidoRepository.getById(Long.parseLong(idPedido));
	}

	public Cliente consultarCliente(String idCliente) {
		return clienteRepository.getById(Long.parseLong(idCliente));
	}
}
