package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.*;
import br.com.alura.clientelo.util.JPAUtil;
import br.com.alura.clientelo.vo.RelatorioVendasPorCategoria;
import jakarta.persistence.EntityManager;
import org.checkerframework.checker.units.qual.C;
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

    private Cliente cliente;

    private List<RelatorioVendasPorCategoria> actualRelatorio;

    @BeforeEach
    void setupEach(){
        JPAUtil jpaUtil = new JPAUtil();
        em = jpaUtil.getEntityManager();
        pedidoDao = new PedidoDao(em);

        automotiva = new Categoria("AUTOMOTIVA", CategoriaStatusEnum.ATIVA);
        livros = new Categoria("LIVROS", CategoriaStatusEnum.ATIVA);

        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
                "cidade fake", "estado fake");
        cliente = new Cliente("Cliente 1", "11111111111", "999999999", endereco);

        Produto produtoAutomotiva = new Produto("produto 1 automotiva", new BigDecimal(10), null, 2, automotiva);
        Produto produtoLivro = new Produto("produto livro", new BigDecimal(10), null, 2, livros);

        Pedido pedido1 = new Pedido(cliente, BigDecimal.ZERO, TipoDescontoEnum.NENHUM);
        pedido1.adicionarItem(new ItemDePedido(2, produtoAutomotiva, BigDecimal.ZERO, TipoDescontoEnum.NENHUM));
        pedido1.adicionarItem(new ItemDePedido(1, produtoLivro, BigDecimal.ZERO, TipoDescontoEnum.NENHUM));

        Pedido pedido2 = new Pedido(cliente,BigDecimal.ZERO, TipoDescontoEnum.NENHUM);
        pedido2.adicionarItem(new ItemDePedido(1, produtoAutomotiva, BigDecimal.ZERO, TipoDescontoEnum.NENHUM));

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

        actualRelatorio = new ArrayList<>();
        actualRelatorio.add(new RelatorioVendasPorCategoria(automotiva.getNome(),new Long(3),  new BigDecimal(30) ));
        actualRelatorio.add(new RelatorioVendasPorCategoria(livros.getNome(),new Long(1),  new BigDecimal(10) ));
    }


    @Test
    void deveriaCadastrar() {
    }

    @Test
    void deveriaRetornarVendasPorCategoria() {
        List<RelatorioVendasPorCategoria> relatorio = pedidoDao.vendasPorCategoria();
        assertEquals(relatorio, actualRelatorio);
    }
}