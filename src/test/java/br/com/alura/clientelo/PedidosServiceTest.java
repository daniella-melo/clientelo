package br.com.alura.clientelo;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.service.PedidosService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PedidosServiceTest {

    private PedidosService pedidosService;
    private static List<Pedido> pedidos;
    private static List<Produto> produtosVendidos;
    @BeforeAll
    static void setup(){
        pedidos = new ArrayList<>();
        pedidos.add(new Pedido("AUTOMOTIVA","Jogo de pneus","CARLOS", new BigDecimal(1000),1, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA","Jogo de pneus","BIA", new BigDecimal(2000),2, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA","Tapete de carro","BIA", new BigDecimal(400),2, LocalDate.now()));
        pedidos.add(new Pedido("LIVROS","Clean Code","BIA", new BigDecimal(70),6, LocalDate.now()));

        produtosVendidos = new ArrayList<>();
        produtosVendidos.add(new Produto("Clean Code", "LIVROS", new BigDecimal(70)));
        produtosVendidos.add(new Produto("Jogo de pneus", "AUTOMOTIVA", new BigDecimal(1000)));
        produtosVendidos.add(new Produto("Tapete de carro", "AUTOMOTIVA",   new BigDecimal(200)));
    }

    @BeforeEach
    void beforeEach() {
        pedidosService = new PedidosService(pedidos);
    }

    @Test
    void deveriaRetornarListaOrdenadaPorProdutoMaisVendido() {
        List<Produto> actualListProdutos = pedidosService.produtoMaisVendidos();

        for (int i = 0; i < actualListProdutos.size(); i++){
            assertEquals(produtosVendidos.get(i).getNome(), actualListProdutos.get(i).getNome());
            assertEquals(produtosVendidos.get(i).getQtdDeVendas(), actualListProdutos.get(i).getQtdDeVendas());
            assertEquals(produtosVendidos.get(i).getCategoria(), actualListProdutos.get(i).getCategoria());
        }
    }

    @Test
    void metodoDeProdutosMaisVendidosDeveriaRetornarNullCasoListaDePedidosSejaNull(){
        pedidosService = new PedidosService(null);
        List<Produto> listProdutos = pedidosService.produtoMaisVendidos();
        assertEquals(null, listProdutos);
    }
}