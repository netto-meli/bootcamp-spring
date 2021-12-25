package com.bootcamp.exercicios.diploma.controller;

/*
É necessário desenvolver uma API que receba um aluno que contenha o seu "nome",
e todas as disciplinas aprovadas com "nome" e "nota", é necessário calcular a média
das notas obtidas ao longo do curso e gerar o diploma com uma “mensagem”,
“média” e “aluno”.
Se a média do aluno for superior a 9, deve ser reconhecido com um parabéns.
 */

import com.bootcamp.exercicios.diploma.service.DiplomaService;
import com.bootcamp.exercicios.diploma.entity.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/diploma")
public class DiplomaController {

	@Autowired
	private DiplomaService diplomaService;

	/*
JSON ALUNO
{
  "nome": "",
  "listaDisciplinas": [
    {
      "disciplina": "",
      "nota": 0.0
    }
  ]
}
	*/
	@PostMapping("/cadastraAluno")
	public Aluno cadastrarAluno(@RequestBody Aluno aluno){
		diplomaService.adicionaAluno(aluno);
		return aluno;
	}

	@GetMapping("/exibirDiploma/{nome}")
	public String exibirAlunoComMedia(@PathVariable String nome){
		return diplomaService.getAlunoComMedia(nome);
	}
}
