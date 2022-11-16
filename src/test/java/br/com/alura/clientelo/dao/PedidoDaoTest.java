package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.*;
import br.com.alura.clientelo.util.JPAUtil;
import br.com.alura.clientelo.vo.RelatorioVendasPorCategoria;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoDaoTest {

    private Categoria automotiva;
    private Categoria livros;

    private EntityManager em;
    private PedidoDao pedidoDao;
    private ProdutoDao produtoDao;
    private Cliente cliente;

    private List<RelatorioVendasPorCategoria> actualRelatorioVendasPorCategoria;
    private Produto produtoAutomotiva;
    private Produto produtoLivro;

    private Pedido pedido1;
    private Pedido pedido2;
    @BeforeEach
    void setupEach(){
        JPAUtil jpaUtil = new JPAUtil();
        em = jpaUtil.getEntityManager();
        pedidoDao = new PedidoDao(em);
        produtoDao = new ProdutoDao(em);

        automotiva = new Categoria("AUTOMOTIVA", CategoriaStatusEnum.ATIVA);
        livros = new Categoria("LIVROS", CategoriaStatusEnum.ATIVA);

        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
                "cidade fake", "estado fake");
        cliente = new Cliente("Cliente 1", "11111111111", "999999999", endereco);

        produtoAutomotiva = new Produto("produto 1 automotiva", new BigDecimal(10), null, 2, automotiva);
        produtoLivro = new Produto("produto livro", new BigDecimal(10), null, 2, livros);

        pedido1 = new Pedido(cliente, BigDecimal.ZERO, TipoDescontoEnum.NENHUM);
        pedido1.adicionarItem(new ItemDePedido(2, produtoAutomotiva, BigDecimal.ZERO, TipoDescontoEnum.NENHUM));
        pedido1.adicionarItem(new ItemDePedido(1, produtoLivro, BigDecimal.ZERO, TipoDescontoEnum.NENHUM));

        pedido2 = new Pedido(cliente,BigDecimal.ZERO, TipoDescontoEnum.NENHUM);
        pedido2.adicionarItem(new ItemDePedido(2, produtoAutomotiva, BigDecimal.ZERO, TipoDescontoEnum.NENHUM));

        em.getTransaction().begin();
        em.persist(automotiva);
        em.persist(livros);
        em.persist(cliente);
        em.flush();

        em.persist(produtoAutomotiva);
        em.persist(produtoLivro);
        em.flush();

        em.persist(pedido1);
        em.persist(pedido2);
        em.getTransaction().commit();

        actualRelatorioVendasPorCategoria = new ArrayList<>();
        actualRelatorioVendasPorCategoria.add(new RelatorioVendasPorCategoria(automotiva.getNome(),new Long(4),  new BigDecimal(40) ));
        actualRelatorioVendasPorCategoria.add(new RelatorioVendasPorCategoria(livros.getNome(),new Long(1),  new BigDecimal(10) ));
    }

    @AfterEach
    void afterEach(){
        em.getTransaction().begin();
        em.remove(pedido1);
        em.remove(pedido2);

        em.flush();

        em.remove(produtoAutomotiva);
        em.remove(produtoLivro);
        em.flush();

        em.remove(automotiva);
        em.remove(livros);
        em.remove(cliente);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void deveriaCadastrar() {
    }

    @Test
    void deveriaRetornarVendasPorCategoria() {
        List<RelatorioVendasPorCategoria> relatorio = pedidoDao.vendasPorCategoria();
        assertEquals(relatorio, actualRelatorioVendasPorCategoria);
    }

    @Test
    void deveriaRetornarProdutosMaisVendidos(){
        List<Produto> maisVendidos = produtoDao.produtosMaisVendidos();
        assertEquals(1, maisVendidos.size());
        assertEquals(maisVendidos.get(0), produtoAutomotiva);
    }

}