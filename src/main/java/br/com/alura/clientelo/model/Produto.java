package br.com.alura.clientelo.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Produto {
    private String nome;
    private String categoria;

    private int qtdDeVendas;

    private  BigDecimal precoUnitario;

    public Produto(String nome, String categoria, BigDecimal precoUnitario) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoUnitario = precoUnitario;
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
    public BigDecimal getPrecoUnitario() { return precoUnitario;}

    public void adicionaVenda(int qntd){
        this.qtdDeVendas += qntd;
    }

}
