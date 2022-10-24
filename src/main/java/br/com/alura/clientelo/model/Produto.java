package br.com.alura.clientelo.model;

public class Produto {
    private String nome;
    private String categoria;

    private int qtdDeVendas;

    public Produto(String nome, String categoria, int qtdDeVendas) {
        this.nome = nome;
        this.categoria = categoria;
        this.qtdDeVendas = qtdDeVendas;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getQtdDeVendas() {
        return qtdDeVendas;
    }
}
