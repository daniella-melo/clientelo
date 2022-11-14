package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Endereco;
import br.com.alura.clientelo.util.JPAUtil;
import jakarta.persistence.EntityManager;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteDaoTest {

    private static Cliente cliente1;
    private static Cliente cliente2;

    private static List<Cliente> todosOsClientes;
    private static ClienteDao clienteDao;
    private static EntityManager em;

    @BeforeAll
    static void setup(){
        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
           "cidade fake", "estado fake");
        //Cliente(String nome, String CPF, String telefone, Endereco endereco) {
        cliente1 = new Cliente("Cliente 1", "11111111111", "999999999", endereco);
        cliente2 = new Cliente("Cliente 2", "22222222222", "999999999", endereco);

        todosOsClientes = new ArrayList<>();
        todosOsClientes.add(cliente1);
        todosOsClientes.add(cliente2);
    }

    @BeforeEach
    void setupEach(){
        JPAUtil jpaUtil = new JPAUtil();
        em = jpaUtil.getEntityManager();
        clienteDao = new ClienteDao(em);
    }

    @Test
    void deveriaCadastrar(){
        em.getTransaction().begin();
        clienteDao.cadastra(cliente1);
        em.flush();
        em.remove(cliente1);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void deveriaAtualizar(){
        em.getTransaction().begin();
        cliente1.setNome("Cliente 1 Atualizacao");
        clienteDao.atualiza(cliente1);
        em.flush();
        em.remove(cliente1);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void deveriaRemover(){
        em.getTransaction().begin();
        clienteDao.remove(cliente1);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void deveriaListarTodos(){
        List<Cliente> returnedClientes = new ArrayList<>();
        em.getTransaction().begin();
        clienteDao.cadastra(cliente1);
        clienteDao.cadastra(cliente2);
        em.flush();
        returnedClientes = clienteDao.listaTodos();
        clienteDao.remove(cliente1);
        clienteDao.remove(cliente2);
        em.getTransaction().commit();
        em.close();

        assertEquals(returnedClientes, todosOsClientes);
    }

    @Test
    void deveriaListarPorNome(){
        Cliente returnedCliente = new Cliente();
        em.getTransaction().begin();
        clienteDao.cadastra(cliente1);
        em.flush();
        returnedCliente = clienteDao.listaPorNome(cliente1.getNome());
        em.remove(cliente1);
        em.getTransaction().commit();
        em.close();

        assertEquals(returnedCliente.getNome(), cliente1.getNome());
    }
}