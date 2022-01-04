package com.bootcamp.exercicios.diploma.controller;

/*
É necessário desenvolver uma API que receba um aluno que contenha o seu "nome",
e todas as disciplinas aprovadas com "nome" e "nota", é necessário calcular a média
das notas obtidas ao longo do curso e gerar o diploma com uma “mensagem”,
“média” e “aluno”.
Se a média do aluno for superior a 9, deve ser reconhecido com um parabéns.
 */

import com.bootcamp.exercicios.diploma.dto.AlunoDTO;
import com.bootcamp.exercicios.diploma.service.DiplomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
	public AlunoDTO cadastrarAluno(@RequestBody @Valid  AlunoDTO alunoDTO){
		diplomaService.adicionaAluno(AlunoDTO.converte(alunoDTO));
		return alunoDTO;
	}

	/*
	Message Mensagem que informa que o diploma foi gerado com sua média

	Average Média do aluno

	student Name: Nome do alumno,
	mínimo 8 caracteres, máximo 50 caracteres
	Tipos de caracteres permitidos de a-z

	subjects
	List<SubjectDTO> subjects
	Lista de matérias com notas
	Name: Nome de matéria
	mínimo 8 caracteres, máximo 50 caracteres
	Tipos de caracteres permitidos de a-z
	note: nota da matéria
	mínimo 1 caracter, máximo 2 caracteres
	Tipos de caracteres permitidos de [0-9]
	 */
	@GetMapping("/exibirDiploma/{nome}")
	public String exibirAlunoComMedia(@PathVariable String nome){
		return diplomaService.getAlunoComMedia(nome);
	}
}
