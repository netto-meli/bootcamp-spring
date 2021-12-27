package com.bootcamp.exercicios.loja.controller;

/*
1. Crie uma API de gestão de clientes em uma loja.
Um cliente deve ter id, nome, cpf, email e telefone.
2. Cada cliente pode ter diversos pedidos registrados
3. Um pedido tem id, produtos e valor total
4. Um produto deve ter id, descrição, cor, quantidade e preço
5. Você deve conseguir consultar todos os pedidos de um determinado cliente.
 */

import com.bootcamp.exercicios.loja.dto.ClienteDTO;
import com.bootcamp.exercicios.loja.dto.PedidoDTO;
import com.bootcamp.exercicios.loja.dto.ProdutoDTO;
import com.bootcamp.exercicios.loja.entity.Cliente;
import com.bootcamp.exercicios.loja.entity.Pedido;
import com.bootcamp.exercicios.loja.entity.Produto;
import com.bootcamp.exercicios.loja.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/loja")
public class LojaController {

	@Autowired
	private LojaService lojaService;

	@PostMapping("/cadastrarCliente")
	public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody ClienteDTO clienteDTO,
													   UriComponentsBuilder uriBuilder){
		Cliente cliente = ClienteDTO.converte(clienteDTO);
		lojaService.adicionaCliente(cliente);
				URI uri = uriBuilder
				.path("/consultaCliente/{id}")
				.buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).body(ClienteDTO.converte(cliente));
	}

	@PostMapping("/cadastrarProdutos")
	public List<ProdutoDTO> cadastrarProdutos(@RequestBody List<ProdutoDTO> listaProdutosDTO){
		List<Produto> listaProdutos = new ArrayList<>();
		listaProdutosDTO.forEach(pd -> listaProdutos.add(ProdutoDTO.converte(pd)));
		lojaService.adicionaProdutos(listaProdutos);
		List<ProdutoDTO> finalListaProdutosDTO = new ArrayList<>();
		listaProdutos.forEach(p -> finalListaProdutosDTO.add(ProdutoDTO.converte(p)));
		return finalListaProdutosDTO;
	}

	@PostMapping("/realizaPedido/{idCliente}")
	public ResponseEntity<PedidoDTO> realizarPedido(@PathVariable String idCliente,
									@RequestBody List<ProdutoDTO> listaProdutosDTO,
									UriComponentsBuilder uriBuilder) {
		List<Produto> listaProdutos = new ArrayList<>();
		listaProdutosDTO.forEach(pd -> listaProdutos.add(ProdutoDTO.converte(pd)));
		PedidoDTO pedidoDTO = PedidoDTO.converte( lojaService.adicionaPedido(idCliente,listaProdutos) );
		URI uri = uriBuilder
				.path("/consultaPedido/{id}")
				.buildAndExpand(pedidoDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(pedidoDTO);
	}

	@GetMapping("/consultaCliente/{idCliente}")
	public ClienteDTO consultarCliente(@PathVariable String idCliente){
		return ClienteDTO.converte( lojaService.consultarCliente(idCliente) );
	}

	@GetMapping("/consultaPedido/{idPedido}")
	public PedidoDTO consultarPedido(@PathVariable String idPedido){
		return PedidoDTO.converte( lojaService.consultarPedido(idPedido) );
	}

	@GetMapping("/consultaPedidos/{idCliente}")
	public List<PedidoDTO> consultarPedidos(@PathVariable String idCliente){
		List<PedidoDTO> listaPedidosDTO = new ArrayList<>();
		List<Pedido> listaPedidos = lojaService.consultarPedidos(idCliente);
		listaPedidos.forEach( p -> listaPedidosDTO.add( PedidoDTO.converte(p)) );
		return listaPedidosDTO;
	}
}
