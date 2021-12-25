package com.bootcamp.exercicios.diploma.entity;

import java.util.List;

public class Aluno {
    private final String nome;
    private final List<Disciplina> listaDisciplinas;

    public Aluno(String nome, List<Disciplina> listaDisciplinas) {
        this.nome = nome;
        this.listaDisciplinas = listaDisciplinas;
    }

    public String getNome() {
        return nome;
    }

    public List<Disciplina> getListaDisciplinas() {
        return listaDisciplinas;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", listaDisciplinas=" + listaDisciplinas +
                '}';
    }
}