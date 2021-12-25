package com.bootcamp.exercicios.calculadoraImobiliaria.entity;

import java.util.List;

public class Imovel {
    private final String nome;
    private final List<Comodo> listaComodos;

    public Imovel(String nome, List<Comodo> listaComodos) {
        this.nome = nome;
        this.listaComodos = listaComodos;
    }

    public String getNome() {
        return nome;
    }

    public List<Comodo> getListaComodos() {
        return listaComodos;
    }
}