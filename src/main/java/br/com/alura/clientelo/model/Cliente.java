package br.com.alura.clientelo.model;

public class Cliente {
    private String nome;
    private String CPF;
    private String telefone;

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
