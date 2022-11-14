package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoriaDao{

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public Categoria buscarPorId(Long id){
        return em.find(Categoria.class, id);
    }

    public void cadastra(Categoria categoria){
        if(categoria==null) return;
        this.em.persist(categoria);
    }

    public List<Categoria> listaTodas(){
        String jpql = "SELECT p FROM "+ Categoria.class.getName();
        return em.createQuery(jpql, Categoria.class).getResultList();
    }
}
