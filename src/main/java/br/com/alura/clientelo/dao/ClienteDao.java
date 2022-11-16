package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.ItemDePedido;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.vo.RelatorioClienteFiel;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteDao {
    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public Cliente buscaPorId(Long id){
        return em.find(Cliente.class, id);
    }

    public void cadastra(Cliente cliente){
        if(cliente==null) return;
        this.em.persist(cliente);
    }

    public void atualiza(Cliente cliente){
        this.em.merge(cliente);
    }

    public void remove(Cliente cliente){
       cliente = em.merge(cliente);
       this.em.remove(cliente);
    }

    public List<Cliente> listaTodos(){
        String jpql = "SELECT c FROM " + Cliente.class.getName()+ " c";
        return em.createQuery(jpql, Cliente.class).getResultList();
    }

    public Cliente listaPorNome(String nome){
        String query = "SELECT c FROM " + Cliente.class.getName() + " c WHERE c.nome = '" + nome +"'";
        return em.createQuery(query, Cliente.class).getSingleResult();
    }

    public List<RelatorioClienteFiel> clientesFieis(){
        String query = "SELECT new br.com.alura.clientelo.vo.RelatorioClienteFiel (" +
                "c.nome, COUNT(p.id), " +
                "SUM(ip.precoUnitario * ip.quantidade)) rc " +
                "FROM " + Cliente.class.getName() + " c " +
                "JOIN " + Pedido.class.getName() + " p on p.cliente = c " +
                "JOIN " + ItemDePedido.class.getName() + " ip on ip.pedido = p " +
                "GROUP BY c.nome ORDER BY COUNT(p.id) DESC";
        return em.createQuery(query, RelatorioClienteFiel.class).getResultList();
    }

    public List<RelatorioClienteFiel> clientesMaisLucrativos(){
        String query = "SELECT new br.com.alura.clientelo.vo.RelatorioClienteFiel (" +
                "c.nome, COUNT(p.id), " +
                "SUM(ip.precoUnitario * ip.quantidade)) rc " +
                "FROM " + Cliente.class.getName() + " c " +
                "JOIN " + Pedido.class.getName() + " p on p.cliente = c " +
                "JOIN " + ItemDePedido.class.getName() + " ip on ip.pedido = p " +
                "GROUP BY c.nome ORDER BY SUM(ip.precoUnitario * ip.quantidade) DESC LIMIT 2";
        return em.createQuery(query, RelatorioClienteFiel.class).getResultList();
    }
}
