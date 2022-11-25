package br.com.alura.clientelo.controller.form;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.CategoriaStatusEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class CategoriaForm {

    @NotEmpty
    @NotNull
    @Length(min = 2)
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
