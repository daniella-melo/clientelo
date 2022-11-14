package br.com.alura.clientelo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name= "item_pedido")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "preco_unitario", nullable = false, scale = 2)
    private BigDecimal precoUnitario;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "pedido_id", nullable = false)
    @ManyToOne
    private Pedido pedido;

    @Column(name = "produto_id", nullable = false)
    @ManyToOne
    private Produto produto;

    @Column(name = "desconto")
    private String desconto;

    @Column(name = "tipo_desconto", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDescontoEnum tipoDesconto;

    public ItemDePedido(int quantidade, Pedido pedido, Produto produto, String desconto, TipoDescontoEnum tipoDesconto) {
        this.precoUnitario = produto.getPrecoUnitario();
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
    }

    public Long getId() {
        return id;
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

    public void setPedido(Pedido pedido) {
        this.pedido= pedido;
    }
}
