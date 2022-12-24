package br.com.alura.clientelo.aplicacao.dao.produto;

import br.com.alura.clientelo.dominio.model.pedido.ItemDePedido;
import br.com.alura.clientelo.dominio.model.produto.Produto;
import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public Produto buscaPorId(Long id){
        return em.find(Produto.class, id);
    };

    public void cadastra(Produto produto){
        if(produto==null) return;
        this.em.persist(produto);
    };

    public List<Produto> listaTodos(){
        String jpql = "SELECT p FROM "+ Produto.class.getName() + " p";
        return em.createQuery(jpql, Produto.class).getResultList();
    };

    public List<Produto> listaIndisponiveis(){
        String query = "SELECT p FROM "+ Produto.class.getName() + " p WHERE p.qntEmEstoque = 0" ;
        return em.createQuery(query, Produto.class).getResultList();
    };

    public List<Produto> produtosMaisVendidos(){
        String query = "SELECT p FROM " + Produto.class.getName() + " p " +
                "JOIN " + ItemDePedido.class.getName() + " ip on ip.produto = p " +
                "GROUP BY p.id HAVING SUM(ip.quantidade) > 3";
        return em.createQuery(query, Produto.class).getResultList();
    }
}