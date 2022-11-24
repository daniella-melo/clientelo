package br.com.alura.clientelo.controller.form;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.CategoriaStatusEnum;

public class CategoriaForm {
    private String nome;

    public CategoriaForm(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Categoria converter() {
        return new Categoria(this.nome, CategoriaStatusEnum.ATIVA);
    }
}
