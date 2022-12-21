package br.com.alura.clientelo.controller.produto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProdutoListagemDto {

    @JsonProperty("Nome")
    private String nome;

    @JsonProperty("Preço")
    private BigDecimal preco;
    @JsonProperty("Descrição")
    private String descricao;
    @JsonProperty("Quantidade em Estoque")
    private int qntEmEstoque;

    @JsonProperty("ID da Categoria")
    private Long idCategoria;

    @JsonProperty("Nome da Categoria")
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
