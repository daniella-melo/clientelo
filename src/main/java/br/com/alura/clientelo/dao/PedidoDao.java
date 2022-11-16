package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.ItemDePedido;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.vo.RelatorioVendasPorCategoria;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastra(Pedido pedido) {
        if (pedido == null) return;
        this.em.persist(pedido);
    }

    public List<RelatorioVendasPorCategoria> vendasPorCategoria() {
        String query = "SELECT new br.com.alura.clientelo.vo.RelatorioVendasPorCategoria(" +
                "ip.produto.categoria.nome, SUM(ip.quantidade), " +
                "SUM(ip.precoUnitario*ip.quantidade)) rp " +
                "FROM " + ItemDePedido.class.getName() + " ip " +
                "GROUP BY ip.produto.categoria.nome";
       return em.createQuery(query, RelatorioVendasPorCategoria.class).getResultList();
    }

    public List<Produto> produtosMaisVendidos(){
        String query = "SELECT p FROM " + Produto.class.getName() + " p " +
                "JOIN " + ItemDePedido.class.getName() + " ip on ip.produto = p " +
                "GROUP BY p.id HAVING SUM(ip.quantidade) > 3";
        return em.createQuery(query, Produto.class).getResultList();
    }
}