package com.bootcamp.exercicios.romano.controller;

/*
Os romanos eram um grupo inteligente. Eles conquistaram a maior parte da Europa e
a governaram por centenas de anos. Eles inventaram estradas de concreto e retas e
até biquínis. No entanto, uma coisa que eles nunca descobriram foi o número zero.
Isso fez com que escrever e namorar histórias extensas de suas façanhas fosse um
pouco mais desafiador, mas o sistema numérico que eles criaram ainda está em uso
hoje. Por exemplo, a BBC usa algarismos romanos para datar seus programas.
Os romanos escreviam números usando letras: I, V, X, L, C, D, M. (observe que essas
letras têm muitas linhas retas e, portanto, são fáceis de cortar em pedras)
Desenvolva uma API para converter um número decimal em um número romano.
Alguns casos de teste:
1 → I
10 → X
7 → VII
15 → XV
 */

import com.bootcamp.exercicios.romano.service.RomanosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RomanosController {

	@Autowired
	private RomanosService romanosService;

	@GetMapping("/romanos")
	public String romanizarNumero(@RequestParam(value = "numeroConverter", defaultValue = "0") String numero) {
		if (numero.equals("0")) {
			return "Não existe 0 em algarismos romanos";
		}
		String romano = romanosService.convertToRoman(numero);
		return String.format("O número " + numero + " em algarismos romanos é: %s!", romano);
	}
}
