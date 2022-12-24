package br.com.alura.clientelo.aplicacao.controller.pedido.dto;

import br.com.alura.clientelo.dominio.model.pedido.ItemDePedido;

import java.math.BigDecimal;

public class ItemPedidoDto {

    private Long id;
    private String nome;
    private String nomeCategoria;
    private int quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal valor;
    private BigDecimal desconto;

    public ItemPedidoDto(ItemDePedido item) {
        this.id = item.getProduto().getId();
        this.nome = item.getProduto().getNome();
        this.nomeCategoria = item.getProduto().getCategoria().getNome();
        this.quantidade = item.getQuantidade();
        this.precoUnitario = item.getPrecoUnitario();
        this.valor = item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade()));
        this.desconto = item.getDesconto();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }
}
