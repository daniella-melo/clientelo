package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.CategoriaStatusEnum;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.util.JPAUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoDaoTest {

    private static Produto produtoDisponivel;
    private static Produto produtoIndisponivel;

    private static List<Produto> todosOsprodutos;

    private static ProdutoDao produtoDao;

    private static CategoriaDao categoriaDao;

    private static Categoria categoria;
    private static EntityManager em;

    @BeforeEach
    void setupEach(){
        JPAUtil jpaUtil = new JPAUtil();
        em = jpaUtil.getEntityManager();
        produtoDao = new ProdutoDao(em);
        categoriaDao = new CategoriaDao(em);

        categoria = new Categoria("AUTOMOTIVA", CategoriaStatusEnum.ATIVA);

        em.getTransaction().begin();
        categoriaDao.cadastra(categoria);
        em.getTransaction().commit();

        produtoDisponivel = new Produto("Produto 1", new BigDecimal(10) ,null, 2, categoria);
        produtoIndisponivel = new Produto("Produto 2",new BigDecimal(10), "descricao produto indisponivel",
                0, categoria);

        todosOsprodutos = new ArrayList<>();
        todosOsprodutos. add(produtoDisponivel);
        todosOsprodutos.add(produtoIndisponivel);
    }
    @Test
    void deveriaBuscaPorId() {
        em.getTransaction().begin();
        produtoDao.cadastra(produtoDisponivel);
        em.flush();
        Produto returnedProduto = produtoDao.buscaPorId(produtoDisponivel.getId());
        em.remove(produtoDisponivel);
        em.remove(categoria);
        em.getTransaction().commit();
        em.close();

        assertEquals(returnedProduto, produtoDisponivel);
    }

    @Test
    void deveriaCadastrar() {
        em.getTransaction().begin();
        produtoDao.cadastra(produtoDisponivel);
        em.flush();
        em.remove(produtoDisponivel);
        em.remove(categoria);
        em.getTransaction().commit();
        em.close();

    }

    @Test
    void deveriaListarTodos() {
        List<Produto> returnedProdutos = new ArrayList<>();
        em.getTransaction().begin();
        produtoDao.cadastra(produtoDisponivel);
        produtoDao.cadastra(produtoIndisponivel);
        em.flush();
        returnedProdutos = produtoDao.listaTodos();
        em.remove(produtoDisponivel);
        em.remove(produtoIndisponivel);
        em.remove(categoria);
        em.getTransaction().commit();
        em.close();

        assertEquals(returnedProdutos, todosOsprodutos);
    }

    @Test
    void deveriaListarIndisponiveis() {
        List<Produto> returnedProdutos = new ArrayList<>();
        em.getTransaction().begin();
        produtoDao.cadastra(produtoDisponivel);
        produtoDao.cadastra(produtoIndisponivel);
        em.flush();
        returnedProdutos = produtoDao.listaIndisponiveis();
        em.remove(produtoDisponivel);
        em.remove(produtoIndisponivel);
        em.remove(categoria);
        em.getTransaction().commit();
        em.close();

        assertEquals(1, returnedProdutos.size());
        assertEquals(returnedProdutos.get(0), produtoIndisponivel);
    }
}