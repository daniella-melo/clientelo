package br.com.alura.clientelo.aplicacao.controller.categoria.dto;

import br.com.alura.clientelo.dominio.model.categoria.Categoria;
import br.com.alura.clientelo.dominio.model.categoria.CategoriaStatusEnum;

public class CategoriaDto {

    private Long id;
    private String nome;
    private CategoriaStatusEnum status;


    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaStatusEnum getStatus() {
        return status;
    }
}
