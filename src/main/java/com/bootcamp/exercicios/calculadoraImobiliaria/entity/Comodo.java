package com.bootcamp.exercicios.calculadoraImobiliaria.entity;

public class Comodo {
    private final String nome;
    private final double largura;
    private final double comprimento;

    public Comodo(String nome, double largura, double comprimento) {
        this.nome = nome;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    public double getLargura() {
        return largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public String getNome() {
        return nome;
    }
}