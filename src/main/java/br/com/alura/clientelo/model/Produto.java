package br.com.alura.clientelo.model;

import jakarta.persistence.*;

import javax.sound.sampled.Port;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "quantidade_estoque", nullable = false)
    private int qntEmEstoque;

    @Column(name = "categoria", nullable = false)
    private Categoria categoria;

    public Produto(String nome, String descricao, int qntEmEstoque, Categoria categoria) {
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

    public void setNome(String nome) {
        this.nome = nome;
    }

}
