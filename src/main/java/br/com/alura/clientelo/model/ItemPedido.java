package br.com.alura.clientelo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "desconto")
    private String desconto;

    @Column(name = "tipo_desconto", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDescontoEnum tipoDesconto;

    public ItemPedido(BigDecimal precoUnitario, int quantidade, Pedido pedido, Produto produto, String desconto, TipoDescontoEnum tipoDesconto) {
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getDesconto() {
        return desconto;
    }

    public TipoDescontoEnum getTipoDesconto() {
        return tipoDesconto;
    }


}
