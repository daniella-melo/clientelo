package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Endereco;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.TipoDescontoEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PedidoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaRetornarDetalhesDoPedido() throws Exception {
        URI uri = new URI("/pedidos/{id}");

        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
                "cidade fake", "estado fake");
        Cliente cliente = new Cliente("Cliente 1", "11111111111", "999999999", endereco);

        Pedido pedido = new Pedido(cliente, TipoDescontoEnum.NENHUM);
        em.persist(pedido);

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .content(String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
