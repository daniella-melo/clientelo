package br.com.alura.clientelo.controller.dto;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.CategoriaStatusEnum;

public class CategoriaDto {

    private String nome;
    private CategoriaStatusEnum status;


    public CategoriaDto(Categoria categoria) {
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
    }


    public String getNome() {
        return nome;
    }

    public CategoriaStatusEnum getStatus() {
        return status;
    }
}
