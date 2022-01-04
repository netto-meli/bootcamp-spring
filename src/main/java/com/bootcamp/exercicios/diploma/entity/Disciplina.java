package com.bootcamp.exercicios.diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Disciplina {
    private final String disciplina;
    private final Double nota;
}
