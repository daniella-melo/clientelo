package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.CategoriaStatusEnum;
import br.com.alura.clientelo.util.JPAUtil;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaDaoTest {

    private static Categoria categoria1;
    private static Categoria categoria2;

    private static List<Categoria> todasAsCategorias;

    private static EntityManager em;
    private static CategoriaDao categoriaDao;

    @BeforeAll
    static void setup(){
        categoria1 = new Categoria("AUTOMOTIVA", CategoriaStatusEnum.ATIVA);
        categoria2 = new Categoria("LIVROS", CategoriaStatusEnum.ATIVA);

        todasAsCategorias = new ArrayList<>();
        todasAsCategorias.add(categoria1);
        todasAsCategorias.add(categoria2);
    }

    @BeforeEach
    void setupEach(){
        JPAUtil jpaUtil = new JPAUtil();
        em = jpaUtil.getEntityManager();
        categoriaDao = new CategoriaDao(em);
    }

    @Test
    void deveriaBuscarCategoriaPorId() {
        Categoria returnedCategoria = new Categoria();
        em.getTransaction().begin();
        categoriaDao.cadastra(categoria1);
        em.flush();
        returnedCategoria = categoriaDao.buscarPorId(categoria1.getId());
        em.flush();
        em.remove(categoria1);
        em.getTransaction().commit();
        em.close();

        assertEquals(returnedCategoria, categoria1);
    }

    @Test
    void deveriaCadastrarCategoria() {
        em.getTransaction().begin();
        categoriaDao.cadastra(categoria1);
        em.flush();
        em.remove(categoria1);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void deverialistaTodasAsCategorias() {
        List<Categoria> returnedListCategoria = new ArrayList<>();
        em.getTransaction().begin();
        categoriaDao.cadastra(categoria1);
        categoriaDao.cadastra(categoria2);
        em.flush();
        returnedListCategoria = categoriaDao.listaTodas();
        em.remove(categoria1);
        em.remove(categoria2);
        em.getTransaction().commit();
        em.close();

        assertEquals(returnedListCategoria, todasAsCategorias);
    }
}