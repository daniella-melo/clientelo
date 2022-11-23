package br.com.alura.clientelo.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "preco_unitario", nullable = false, scale = 2)
    private BigDecimal precoUnitario;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade_estoque", nullable = false)
    private int qntEmEstoque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Produto(String nome, BigDecimal precoUnitario, String descricao, int qntEmEstoque, Categoria categoria) {
        this.precoUnitario = precoUnitario;
        this.nome = nome;
        this.descricao = descricao;
        this.qntEmEstoque = qntEmEstoque;
        this.categoria = categoria;
    }

    public Produto(){}
    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getQntEmEstoque() {
        return qntEmEstoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
