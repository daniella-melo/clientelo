package br.com.alura.clientelo.aplicacao.dao.categoria;

import br.com.alura.clientelo.dominio.model.categoria.Categoria;

import javax.persistence.EntityManager;
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
        String query = "SELECT c FROM "+ Categoria.class.getName() +" c";
        return em.createQuery(query, Categoria.class).getResultList();
    }
}
