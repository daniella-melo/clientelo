package br.com.alura.clientelo.service;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Endereco;
import br.com.alura.clientelo.projecao.ClienteFielProjecao;
import br.com.alura.clientelo.repository.ClienteRepository;
import br.com.alura.clientelo.vo.RelatorioClienteFiel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@SpringBootTest
public class ClienteServiceTest {

    private static Cliente cliente1;
    private static Cliente cliente2;

    private static List<Cliente> todosOsClientes;

    @Autowired
    private static ClienteService subject;

    @Autowired
    private static ClienteRepository repository;

    @BeforeAll
    static void setup(){
        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
                "cidade fake", "estado fake");

        cliente1 = new Cliente("Cliente 1", "11111111111", "999999999", endereco);
        cliente2 = new Cliente("Cliente 2", "22222222222", "999999999", endereco);

        todosOsClientes = new ArrayList<>();
        todosOsClientes.add(cliente1);
        todosOsClientes.add(cliente2);

        repository.saveAll(todosOsClientes);
    }

    @AfterAll
    static void after(){
        repository.deleteAll(todosOsClientes);
    }

    @Test
    void getClientesFieis() {
        List<ClienteFielProjecao> returnedClientesFieis = subject.getClientesFieis();
        assertEquals(returnedClientesFieis.get(0), cliente2);
        assertEquals(returnedClientesFieis.get(1), cliente1);
    }

    @Test
    void getClientesMaisLucrativos() {
        List<ClienteFielProjecao> returnedClientesMaisLucrativos = subject.getClientesFieis();
        assertEquals(returnedClientesMaisLucrativos.get(0), cliente1);
        assertEquals(returnedClientesMaisLucrativos.get(1), cliente2);
    }
}