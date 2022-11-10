package br.com.alura.clientelo.model;

import javax.sound.sampled.Port;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Produto {
    private String nome;

    private String categoria;

    private int qtdDeVendas;

    private  BigDecimal precoUnitario;

    private int qntEmEstoque;

    private String descricao;
    public Produto(String nome, String descricao, String categoria, BigDecimal precoUnitario) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoUnitario = precoUnitario;
    }

    public Produto(){}
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

    public int getQntEmEstoque() {
        return qntEmEstoque;
    }

    public String getDescricao() {
        return descricao;
    }


    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQtdDeVendas(int qtdDeVendas) {
        this.qtdDeVendas = qtdDeVendas;
    }
}
