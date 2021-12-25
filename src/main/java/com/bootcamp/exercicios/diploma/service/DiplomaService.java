package com.bootcamp.exercicios.diploma.service;

import com.bootcamp.exercicios.diploma.entity.Aluno;
import com.bootcamp.exercicios.diploma.entity.Disciplina;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiplomaService {
	List<Aluno> listaAlunos = new ArrayList<>();

	public void adicionaAluno(Aluno aluno){
		listaAlunos.add(aluno);
	}

	public String getAlunoComMedia(String nome){
		double media;
		Aluno aluno = listaAlunos.stream().filter( al -> al.getNome().equals(nome) ).findFirst().orElse(null);
		if (aluno != null) {
			media = aluno.getListaDisciplinas().stream().mapToDouble(Disciplina::getNota).sum();
			media /= aluno.getListaDisciplinas().size();
			return  aluno + "\nMédia: "+ media;
		} else
			return "Aluno inexistente";
	}
}
