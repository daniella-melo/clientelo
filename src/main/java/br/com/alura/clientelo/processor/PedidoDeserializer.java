package br.com.alura.clientelo.processor;

import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class PedidoDeserializer {

    private String categoria;
    private String produto;
    private String cliente;
    private BigDecimal preco;
    private int quantidade;
    private LocalDate data;


    public PedidoDeserializer() {
    }

    public PedidoDeserializer(@JsonProperty("categoria") String categoria, @JsonProperty("produto") String produto,
            @JsonProperty("cliente") String cliente, @JsonProperty("preco") BigDecimal preco,
            @JsonProperty("quantidade") int quantidade,
            @JsonProperty("data") @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) LocalDate data) {
        if (categoria == null || produto == null || cliente == null
                || preco == null || quantidade == 0 || data == null) {
            throw new NullPointerException();
        }
        this.categoria = categoria;
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
    }


    public Pedido toPedido() {
        Produto newProduto = new Produto();
        newProduto.setNome(produto);
        return new Pedido(categoria, newProduto, cliente, preco, quantidade, data);
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }
}
