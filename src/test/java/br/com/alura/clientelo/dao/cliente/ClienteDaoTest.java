package br.com.alura.clientelo.dao.cliente;

import br.com.alura.clientelo.model.categoria.Categoria;
import br.com.alura.clientelo.model.categoria.CategoriaStatusEnum;
import br.com.alura.clientelo.model.cliente.Cliente;
import br.com.alura.clientelo.model.cliente.Endereco;
import br.com.alura.clientelo.model.pedido.ItemDePedido;
import br.com.alura.clientelo.model.pedido.Pedido;
import br.com.alura.clientelo.model.pedido.desconto.TipoDescontoEnum;
import br.com.alura.clientelo.model.produto.Produto;
import br.com.alura.clientelo.util.JPAUtil;
import br.com.alura.clientelo.vo.cliente.RelatorioClienteFiel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteDaoTest {

    private static Cliente cliente1;
    private static Cliente cliente2;

    private static List<Cliente> todosOsClientes;
    private static ClienteDao clienteDao;
    private static EntityManager em;

    private Categoria automotiva;
    private Categoria livros;

    private Produto produtoAutomotiva;
    private  Produto produtoLivro;
    private Pedido pedido1;
    private Pedido pedido2;
    private Pedido pedido3;
    @BeforeEach
    void setupEach(){
        JPAUtil jpaUtil = new JPAUtil();
        em = jpaUtil.getEntityManager();
        clienteDao = new ClienteDao(em);

        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
                "cidade fake", "estado fake");
        //Cliente(String nome, String CPF, String telefone, Endereco endereco) {
        cliente1 = new Cliente("Cliente 1", "11111111111", "999999999", endereco);
        cliente2 = new Cliente("Cliente 2", "22222222222", "999999999", endereco);

        todosOsClientes = new ArrayList<>();
        todosOsClientes.add(cliente1);
        todosOsClientes.add(cliente2);
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

    @Test
    void deveriaRetornarClientesFieis(){
        setupRelatorioDeClientes();

        List<RelatorioClienteFiel> actualClientesFieis = new ArrayList<>();
        actualClientesFieis.add(new RelatorioClienteFiel(cliente2.getNome(), new Long(2), new BigDecimal(30)));
        actualClientesFieis.add(new RelatorioClienteFiel(cliente1.getNome(), new Long(1), new BigDecimal(60)));


        List<RelatorioClienteFiel> returnedClientesFieis = clienteDao.clientesFieis();
        assertEquals(returnedClientesFieis, actualClientesFieis);

        afterRelatorioDeClientes();
    }

    @Test
    void deveriaRetornarClientesMaisLucrativos(){
        setupRelatorioDeClientes();

        List<RelatorioClienteFiel> actualClientesLucrativos = new ArrayList<>();
        actualClientesLucrativos.add(new RelatorioClienteFiel(cliente1.getNome(), new Long(1), new BigDecimal(60)));
        actualClientesLucrativos.add(new RelatorioClienteFiel(cliente2.getNome(), new Long(2), new BigDecimal(30)));

        List<RelatorioClienteFiel> returnedClientesLucrativos = clienteDao.clientesMaisLucrativos();
        assertEquals(returnedClientesLucrativos, actualClientesLucrativos);

        afterRelatorioDeClientes();
    }

    private void setupRelatorioDeClientes(){
        automotiva = new Categoria("AUTOMOTIVA", CategoriaStatusEnum.ATIVA);
        livros = new Categoria("LIVROS", CategoriaStatusEnum.ATIVA);

        produtoAutomotiva = new Produto("produto automotiva", new BigDecimal(10), null, 4, automotiva);
        produtoLivro = new Produto("produto livro", new BigDecimal(10), null, 2, livros);

        pedido1 = new Pedido(cliente1, TipoDescontoEnum.NENHUM);
        pedido1.adicionarItem(new ItemDePedido(6, produtoAutomotiva, BigDecimal.ZERO, TipoDescontoEnum.NENHUM));

        pedido2 = new Pedido(cliente2,TipoDescontoEnum.NENHUM);
        pedido2.adicionarItem(new ItemDePedido(2, produtoAutomotiva, BigDecimal.ZERO, TipoDescontoEnum.NENHUM));

        pedido3 = new Pedido(cliente2,TipoDescontoEnum.NENHUM);
        pedido3.adicionarItem(new ItemDePedido(1, produtoLivro, BigDecimal.ZERO, TipoDescontoEnum.NENHUM));

        em.getTransaction().begin();
        em.persist(automotiva);
        em.persist(livros);
        em.persist(cliente1);
        em.persist(cliente2);
        em.flush();

        em.persist(produtoAutomotiva);
        em.persist(produtoLivro);
        em.flush();

        em.persist(pedido1);
        em.persist(pedido2);
        em.persist(pedido3);
        em.getTransaction().commit();
    }

    private void afterRelatorioDeClientes() {
        em.getTransaction().begin();
        em.remove(pedido1);
        em.remove(pedido2);
        em.remove(pedido3);

        em.flush();

        em.remove(produtoAutomotiva);
        em.remove(produtoLivro);
        em.flush();

        em.remove(automotiva);
        em.remove(livros);
        em.remove(cliente1);
        em.remove(cliente2);
        em.getTransaction().commit();
        em.close();
    }

}