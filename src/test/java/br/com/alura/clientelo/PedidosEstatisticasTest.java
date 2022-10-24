package br.com.alura.clientelo;

import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PedidosEstatisticasTest {

    private PedidosEstatisticas pedidosEstatisticas;
    private static List<Pedido> pedidos;
    private static List<Produto> produtosVendidos;
    @BeforeAll
    static void setup(){
        pedidos = new ArrayList<>();
        pedidos.add(new Pedido("AUTOMOTIVA","Jogo de pneus","CARLOS", new BigDecimal(1000),1, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA","Jogo de pneus","BIA", new BigDecimal(1000),2, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA","Tapete de carro","BIA", new BigDecimal(400),2, LocalDate.now()));
        pedidos.add(new Pedido("LIVROS","Clean Code","BIA", new BigDecimal(70),6, LocalDate.now()));

        produtosVendidos = new ArrayList<>();
        produtosVendidos.add(new Produto("Clean Code", "LIVROS", 6, new BigDecimal(70)));
        produtosVendidos.add(new Produto("Jogo de pneus", "AUTOMOTIVA", 3, BigDecimal(1000)));
        produtosVendidos.add(new Produto("Tapete de carro", "AUTOMOTIVA", 2,  new BigDecimal(1000)));

    }

    @BeforeEach
    void beforeEach() {
        pedidosEstatisticas = new PedidosEstatisticas(pedidos);
    }

    @Test
    void deveriaRetornarListaOrdenadaPorProdutoMaisVendido() {
        List<Produto> listProdutos = pedidosEstatisticas.produtoMaisVendidos();

        for (int i = 0; i < listProdutos.size(); i++){
            assertEquals(produtosVendidos.get(i).getNome(), listProdutos.get(i).getNome());
            assertEquals(produtosVendidos.get(i).getQtdDeVendas(), listProdutos.get(i).getQtdDeVendas());
            assertEquals(produtosVendidos.get(i).getCategoria(), listProdutos.get(i).getCategoria());
        }
    }

    @Test
    void metodoDeProdutosMaisVendidosDeveriaRetornarNullCasoListaDePedidosSejaNull(){
        pedidosEstatisticas = new PedidosEstatisticas(null);
        List<Produto> listProdutos = pedidosEstatisticas.produtoMaisVendidos();
        assertEquals(null, listProdutos);
    }
}