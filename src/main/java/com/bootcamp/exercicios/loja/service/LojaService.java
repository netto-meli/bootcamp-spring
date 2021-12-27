package com.bootcamp.exercicios.loja.service;

import com.bootcamp.exercicios.loja.entity.Cliente;
import com.bootcamp.exercicios.loja.entity.Pedido;
import com.bootcamp.exercicios.loja.entity.Produto;
import com.bootcamp.exercicios.loja.repository.ClienteRepository;
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

	public void adicionaCliente(Cliente cliente) {
		clienteRepository.set(cliente);
	}

	public void adicionaProdutos(List<Produto> produtos) {
		produtoRepository.add(produtos);
	}

	public Pedido adicionaPedido(String idCliente, List<Produto> listaProdutos) {
		return clienteRepository.addPedido(Long.parseLong(idCliente), pedidoRepository, listaProdutos);
	}

	public List<Pedido> consultarPedidos(String idCliente) {
		return consultarCliente(idCliente).getPedidos();
	}

	public Pedido consultarPedido(String idPedido) {
		return pedidoRepository.get(Long.parseLong(idPedido));
	}

	public Cliente consultarCliente(String idCliente) {
		return clienteRepository.get(Long.parseLong(idCliente));
	}
}
