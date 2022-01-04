package com.bootcamp.exercicios.diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Aluno {
    private final String nome;
    private final List<Disciplina> listaDisciplinas;
}