package com.bootcamp.exercicios.calculadoraImobiliaria.controller;

/*
Desenvolva uma API que receba uma:
Casa com seu "nome", "endereço" e uma lista de "cômodos".
Comodo deve conter os atributos: ”nome", "largura" e "comprimento".

Como requisito funcional é solicitado que a API:
a) Retorne o número total de metros quadrados da casa.
/area/{nomeDaCasa}
b) Retorne o valor da casa tendo em consideração R$ 800 por metro quadrado.
valor/{nomeDaCasa}
c) Retorne o maior cômodo.
/maiorComodo/{nomeDaCasa}
d) Retorne a quantidade de metros quadrados por cômodo.
/areaComodos/{nomeDaCasa}
 */

import com.bootcamp.exercicios.calculadoraImobiliaria.entity.Imovel;
import com.bootcamp.exercicios.calculadoraImobiliaria.service.CalculadoraImobiliariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/calculadoraImobiliaria")
public class CalculadoraImobiliariaController {

	@Autowired
	private CalculadoraImobiliariaService imobiliariaService;

	/*
JSON IMOVEL
{
  "nome": "",
  "listaComodos": [
    {
      "nome": "",
      "largura": 0.0,
      "comprimento": 0.0
    }
  ]
}
	*/
	@PostMapping("/cadastrarCasa")
	public Imovel cadastrarImovel(@RequestBody Imovel imovel){
		imobiliariaService.adicionaImovel(imovel);
		return imovel;
	}

	@GetMapping("/area/{nomeDaCasa}")
	public String calculaAreaImovel(@PathVariable String nomeDaCasa){
		//metros ^2
		return imobiliariaService.getAreaImovel(nomeDaCasa);
	}

	@GetMapping("/areaComodos/{nomeDaCasa}")
	public String calculaAreaComodosImovel(@PathVariable String nomeDaCasa){
		//metros ^2 de cada comodo
		return imobiliariaService.getAreaComodos(nomeDaCasa);
	}

	@GetMapping("valor/{nomeDaCasa}")
	public String calculaValorImovel(@PathVariable String nomeDaCasa){
		//800 reais por metros ^2
		return imobiliariaService.getValorImovel(nomeDaCasa);
	}

	@GetMapping("/maiorComodo/{nomeDaCasa}")
	public String getMaiorComodo(@PathVariable String nomeDaCasa){
		//qual maior comodo
		return imobiliariaService.getMaiorComodo(nomeDaCasa);
	}
}
