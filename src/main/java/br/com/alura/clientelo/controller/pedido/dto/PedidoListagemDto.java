package br.com.alura.clientelo.controller.pedido.dto;

import br.com.alura.clientelo.model.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoListagemDto {

    private LocalDate data;
    @JsonProperty("Valor Total")
    private BigDecimal valorTotal;

    @JsonProperty("Desconto Totais")
    private BigDecimal desconto;

    @JsonProperty("Quantidade de produtos vendidos")
    private int qtdProdutosVendidos;

    @JsonProperty("ID do Cliente")
    private Long idCliente;

    @JsonProperty("Nome do Cliente")
    private String nomeCliente;

    public PedidoListagemDto(LocalDate data, BigDecimal valorTotal, BigDecimal desconto, int qtdProdutosVendidos, Cliente cliente) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
        this.qtdProdutosVendidos = qtdProdutosVendidos;
        this.idCliente = cliente.getId();
        this.nomeCliente = cliente.getNome();
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public int getQtdProdutosVendidos() {
        return qtdProdutosVendidos;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }
}
