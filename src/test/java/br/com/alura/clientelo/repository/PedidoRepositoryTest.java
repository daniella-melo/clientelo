package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.projecao.VendaPorCategoriaProjecao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //nao vai substituir as configs do banco para o em memoria h2
@ActiveProfiles("test")
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaRetornarVendasPorCategoria() {
        List<VendaPorCategoriaProjecao> actualVendas = null;
        //inserir persistencia com o testEntity manager
        List<VendaPorCategoriaProjecao> vendas = repository.findVendasPorCategoria();
        Assert.assertEquals(actualVendas, vendas);
    }
}
