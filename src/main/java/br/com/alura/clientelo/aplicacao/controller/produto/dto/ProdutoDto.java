package br.com.alura.clientelo.aplicacao.controller.produto.dto;

import br.com.alura.clientelo.dominio.model.produto.Produto;

import java.math.BigDecimal;

public class ProdutoDto {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private int qntEmEstoque;
    private Long idCategoria;

    public ProdutoDto(Long id, String nome, BigDecimal preco, String descricao, int qntEmEstoque, Long idCategoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.qntEmEstoque = qntEmEstoque;
        this.idCategoria = idCategoria;
    }

    public ProdutoDto() { }

    public ProdutoDto(Produto novo) {
        this.id = novo.getId();
        this.nome = novo.getNome();
        this.preco = novo.getPrecoUnitario();
        this.descricao = novo.getDescricao();
        this.qntEmEstoque = novo.getQntEmEstoque();
        this.idCategoria = novo.getCategoria().getId();
    }

    public Long getId() {
        return id;
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
}
