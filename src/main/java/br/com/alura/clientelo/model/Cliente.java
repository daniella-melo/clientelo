package br.com.alura.clientelo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false, columnDefinition = "varchar(11)", unique = true)
    private String CPF;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Embedded
    private Endereco endereco;

    public Cliente(String nome, String CPF, String telefone, Endereco endereco) {
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
