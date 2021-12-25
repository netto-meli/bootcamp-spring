package com.bootcamp.exercicios.calculadoraImobiliaria.service;

import com.bootcamp.exercicios.calculadoraImobiliaria.entity.Comodo;
import com.bootcamp.exercicios.calculadoraImobiliaria.entity.Imovel;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculadoraImobiliariaService {
	List<Imovel> listaImoveis = new ArrayList<>();

	public void adicionaImovel(Imovel imovel) {
		listaImoveis.add(imovel);
	}

	public String getAreaComodos(String nomeDaCasa) {
		StringBuilder areaComodos = new StringBuilder();
		Imovel imovel = listaImoveis.stream().filter( im -> im.getNome().equals(nomeDaCasa)).findFirst().orElse(null);
		if (imovel == null) throw new NullPointerException("Imovel inexistente.");
		for (Comodo com : imovel.getListaComodos()) {
			areaComodos.append("Comodo: ").append(com.getNome()).append(" com ")
					.append(calcularAreaIndividual(com)).append(" metros quadrados<br>");
		}
		return "A area de cada comodo da casa " + nomeDaCasa + " é: <br>" + areaComodos;
	}

	public String getAreaImovel(String nomeDaCasa) {
		double areaTotal = calcularAreaTotal(nomeDaCasa);
		return "A area total da casa " + nomeDaCasa + " é " + areaTotal + " metros quadrados.";
	}

	public String getValorImovel(String nomeDaCasa) {
		double valor = calcularAreaTotal(nomeDaCasa) * 800;
		return "O valor da casa " + nomeDaCasa + " é R$ " + NumberFormat.getCurrencyInstance().format(valor);
	}

	public String getMaiorComodo(String nomeDaCasa) {
		String maiorComodo = "";
		double maiorArea = 0;
		Imovel imovel = listaImoveis.stream().filter( im -> im.getNome().equals(nomeDaCasa)).findFirst().orElse(null);
		if (imovel == null) throw new NullPointerException("Imovel inexistente.");
		for (Comodo com : imovel.getListaComodos()) {
			double area = calcularAreaIndividual(com);
			if (area > maiorArea) {
				maiorComodo = com.getNome();
				maiorArea = area;
			}
		}
		return "O maior comodo da casa " + nomeDaCasa + ": " +
				maiorComodo + " com área de " + maiorArea + " metros quadrados.";
	}

	// Calcula area do comodo
	private double calcularAreaIndividual(Comodo comodo){
		return comodo.getLargura() * comodo.getComprimento();
	}

	//Calcula area total do Imovel
	private double calcularAreaTotal(String nomeDaCasa) {
		double areaTotal = 0;
		Imovel imovel = listaImoveis.stream().filter( im -> im.getNome().equals(nomeDaCasa)).findFirst().orElse(null);
		if (imovel == null) throw new NullPointerException("Imovel inexistente.");
		for (Comodo com : imovel.getListaComodos()) areaTotal += calcularAreaIndividual(com);
		return areaTotal;
	}
}
