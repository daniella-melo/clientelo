package br.com.alura.clientelo.controller.dto;

import java.math.BigDecimal;

public class ProdutoListagemDto {

    private String nome;
    private BigDecimal preco;
    private String descricao;
    private int qntEmEstoque;
    private Long idCategoria;
    private String nomeCategoria;

    public ProdutoListagemDto(String nome, BigDecimal preco, String descricao,
                              int qntEmEstoque, Long idCategoria, String nomeCategoria) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.qntEmEstoque = qntEmEstoque;
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQntEmEstoque() {
        return qntEmEstoque;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
