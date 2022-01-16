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
import com.bootcamp.exercicios.loja.dto.ItemPedidoDTO;
import com.bootcamp.exercicios.loja.dto.PedidoDTO;
import com.bootcamp.exercicios.loja.dto.ProdutoDTO;
import com.bootcamp.exercicios.loja.entity.Cliente;
import com.bootcamp.exercicios.loja.entity.ItemPedido;
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
		cliente = lojaService.adicionaCliente(cliente);
				URI uri = uriBuilder
				.path("/consultaCliente/{id}")
				.buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).body(ClienteDTO.converte(cliente, new ArrayList<>(),new ArrayList<>()));
	}

	@PostMapping("/cadastrarProdutos")
	public List<ProdutoDTO> cadastrarProdutos(@RequestBody List<ProdutoDTO> listaProdutosDTO){
		List<Produto> listaProdutos = new ArrayList<>();
		listaProdutosDTO.forEach(pd -> listaProdutos.add(ProdutoDTO.converte(pd)));

		List<Produto> finalListaProdutos = lojaService.adicionaProdutos(listaProdutos);
		List<ProdutoDTO> finalListaProdutosDTO = new ArrayList<>();
		finalListaProdutos.forEach(p -> finalListaProdutosDTO.add(ProdutoDTO.converte(p)));
		return finalListaProdutosDTO;
	}

	@PostMapping("/realizaPedido/{idCliente}")
	public ResponseEntity<PedidoDTO> realizarPedido(@PathVariable String idCliente,
									@RequestBody List<ItemPedidoDTO> listaProdutosDTO,
									UriComponentsBuilder uriBuilder) {
		List<ItemPedido> listaProdutos = new ArrayList<>();
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId( Long.valueOf(idCliente));
		listaProdutosDTO.forEach(ip -> listaProdutos.add(
				ItemPedidoDTO.converte(ip, new PedidoDTO(), clienteDTO)
				));
		Pedido pedidoRealizado = lojaService.adicionaPedido(idCliente,listaProdutos);
		listaProdutos.forEach( lp -> lp.setPedido(pedidoRealizado) );
		PedidoDTO pedidoDTO = PedidoDTO.converte( pedidoRealizado, listaProdutos );
		URI uri = uriBuilder
				.path("/consultaPedido/{id}")
				.buildAndExpand(pedidoDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(pedidoDTO);
	}

	@GetMapping("/consultaCliente/{idCliente}")
	public ClienteDTO consultarCliente(@PathVariable String idCliente){
		Cliente cliente = lojaService.consultarCliente(idCliente);
		List<PedidoDTO> listaPedidosDTO = consultarPedidos(idCliente);
		/*
		List<Pedido> pedidoList = lojaService.consultarPedidos(idCliente);

		List<PedidoDTO> listaPedidosDTO = new ArrayList<>();
		pedidoList.forEach( p -> {
			List<ItemPedido> itemPedidoList = lojaService.consultarItemPedido(p.getId().toString());
			listaPedidosDTO.add( PedidoDTO.converte(p, itemPedidoList));
		} );*/
		// todo arrumar (tá cagado)
		//List<ItemPedido> itemPedidoList = lojaService.consultarItemPedidos(idCliente);
		// ClienteDTO.converte( cliente, pedidoList, new ArrayList<>());//itemPedidoList);
		ClienteDTO clienteDTO = ClienteDTO.converte( cliente, new ArrayList<>(), new ArrayList<>());
		clienteDTO.setPedidos(listaPedidosDTO);

		return clienteDTO;
	}

	@GetMapping("/consultaPedido/{idPedido}")
	public PedidoDTO consultarPedido(@PathVariable String idPedido){
		Pedido pedido = lojaService.consultarPedido(idPedido);
		List<ItemPedido> itemPedidoList = lojaService.consultarItemPedido(idPedido);
		return PedidoDTO.converte( pedido, itemPedidoList );
	}

	@GetMapping("/consultaPedidos/{idCliente}")
	public List<PedidoDTO> consultarPedidos(@PathVariable String idCliente){
		List<Pedido> pedidoList = lojaService.consultarPedidos(idCliente);
		List<PedidoDTO> listaPedidosDTO = new ArrayList<>();
		pedidoList.forEach( p -> {
			List<ItemPedido> itemPedidoList = lojaService.consultarItemPedido(p.getId().toString());
			listaPedidosDTO.add( PedidoDTO.converte(p, itemPedidoList));
		} );
		return listaPedidosDTO;
	}
}
