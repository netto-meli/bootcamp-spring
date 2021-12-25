package com.bootcamp.exercicios.diploma.entity;

public class Disciplina {
    private final String disciplina;
    private final Double nota;

    public Disciplina(String disciplina, Double nota) {
        this.disciplina = disciplina;
        this.nota = nota;
    }

    public Double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "disciplina='" + disciplina + '\'' +
                ", nota=" + nota +
                '}';
    }
}
