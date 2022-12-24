package br.com.alura.clientelo.aplicacao.dao.pedido;

import br.com.alura.clientelo.dominio.model.pedido.ItemDePedido;
import br.com.alura.clientelo.dominio.model.pedido.Pedido;
import br.com.alura.clientelo.dominio.vo.categoria.RelatorioVendasPorCategoria;

import javax.persistence.EntityManager;
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
        String query = "SELECT new br.com.alura.clientelo.vo.categoria.RelatorioVendasPorCategoria(" +
                "ip.produto.categoria.nome, SUM(ip.quantidade), " +
                "SUM(ip.precoUnitario*ip.quantidade)) rp " +
                "FROM " + ItemDePedido.class.getName() + " ip " +
                "GROUP BY ip.produto.categoria.nome";
       return em.createQuery(query, RelatorioVendasPorCategoria.class).getResultList();
    }
}