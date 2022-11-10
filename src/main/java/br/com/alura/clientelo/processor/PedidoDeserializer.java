package br.com.alura.clientelo.processor;

import br.com.alura.clientelo.estatisticas.PedidoEstatistica;
import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Endereco;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
                "cidade fake", "estado fake");
        Cliente newCliente = new Cliente(cliente, "11122233344", "999999999", endereco);
        Pedido pedido = new Pedido(categoria,  newCliente, data);
        PedidoEstatistica pedidoEstatistica = new PedidoEstatistica(pedido,preco,
                new Produto(produto, "descricao", categoria, preco.divide(new BigDecimal(quantidade)).setScale(2, RoundingMode.HALF_UP)), quantidade);
        return pedido;
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
