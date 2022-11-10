package br.com.alura.clientelo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Categoria  {

    private int id;
    private String nome;

    private CategoriaStatusEnum status;

    public Categoria(String nome) {
        if(nome==null){
            throw new NullPointerException();
        }
        this.nome = nome;
        this.status = CategoriaStatusEnum.ATIVA ;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaStatusEnum getStatus() {
        return status;
    }
    @Override
    public String toString(){
        return "CATEGORIA: " + nome + "\n" +
                "QUANTIDADE: " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria that = (Categoria) o;
        return Objects.equals(nome, that.getNome());
    }

    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }
}

