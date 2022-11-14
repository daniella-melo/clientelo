package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteDao {
    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public Cliente buscaPorId(Long id){
        return em.find(Cliente.class, id);
    };

    public void cadastra(Cliente cliente){
        if(cliente==null) return;
        this.em.persist(cliente);
    };

    public void atualiza(Cliente cliente){
        this.em.merge(cliente);
    }

    public void remove(Cliente cliente){
       cliente = em.merge(cliente);
       this.em.remove(cliente);
    }

    public List<Cliente> listaTodos(){
        String jpql = "SELECT c FROM " + Cliente.class.getName()+" c";
        return em.createQuery(jpql, Cliente.class).getResultList();
    };

    public Cliente listaPorNome(String nome){
        String jpql = "SELECT c FROM " + Cliente.class.getName() + " WHERE c.nome = '" + nome +"'";
        return em.createQuery(jpql, Cliente.class).getSingleResult();
    };
}
