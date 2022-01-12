package com.bootcamp.exercicios.restaurante.controller;

/*
1. Crie uma API para controle de pedidos em um restaurante.
2. Um Pedido deve ter id, mesa, lista de pratos solicitados e valor total.
3. Um Prato deve ter id, preço, descrição, quantidade.
4. Uma Mesa deve ter id, lista de pedidos e valor total consumido;
5. Você deve conseguir fazer uma consulta de pedidos por mesa retornando todos os
itens pedidos por esta além do valor total consumido.
6. Crie também um endpoint para fechamento de pedidos adicionando o valor total no
registro de caixa e retirando todos os pedidos para a respectiva mesa.
7. Você deve ser capaz de consultar o valor em caixa
 */

import com.bootcamp.exercicios.restaurante.dto.MesaDTO;
import com.bootcamp.exercicios.restaurante.dto.PedidoDTO;
import com.bootcamp.exercicios.restaurante.dto.PratoDTO;
import com.bootcamp.exercicios.restaurante.entity.Mesa;
import com.bootcamp.exercicios.restaurante.entity.Prato;
import com.bootcamp.exercicios.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;

	@PostMapping("/abreRestaurante/{numeroMesas}")
	public void abreRestaurante(@RequestBody List<PratoDTO> pratos, @PathVariable int numeroMesas){
		restauranteService.abreRestaurante(numeroMesas, PratoDTO.converteList(pratos));
	}

	@GetMapping("/cardapio/")
	public List<PratoDTO> listaPratos(){
		List<Prato> pratos = restauranteService.getCardapio();
		return PratoDTO.converte(pratos);
	}

	@PostMapping("/adicionaPedido/{id}")
	public MesaDTO adicionaPedidoMesa(@RequestBody PedidoDTO pedido, @PathVariable Long id){
		Mesa mesa = restauranteService.adicionaPedidoMesa(id,PedidoDTO.converte(pedido,id));
		return MesaDTO.converte(mesa);
	}

	@GetMapping("/listaPedidos/{id}")
	public MesaDTO listaPedidosMesa(@PathVariable Long id){
		Mesa mesa = restauranteService.getPedidosMesa(id);
		return MesaDTO.converte(mesa);
	}

	@GetMapping("/fechaMesa/{id}")
	public BigDecimal fecharMesa(@PathVariable Long id){
		return restauranteService.fecharMesa(id);
	}

	@GetMapping("/verificaMesasDisponiveis")
	public List<Long> calculaValorImovel(){
		return restauranteService.verificaMesasLivres();
	}

	@GetMapping("/valorCaixa/")
	public BigDecimal valorEmCaixa(){
		return restauranteService.getValorCaixa();
	}
}
