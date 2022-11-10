package br.com.alura.clientelo;

import br.com.alura.clientelo.model.*;
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
        //produtos
        Produto jogoDePneu = new Produto("Jogo de pneus", "descricao jogo de pneus","AUTOMOTIVA", new BigDecimal(1000) );
        jogoDePneu.setQtdDeVendas(3);
        Produto tapeteDeCarro = new Produto("Tapete de carro","descricao tapete de carro","AUTOMOTIVA", new BigDecimal(200) );
        tapeteDeCarro.setQtdDeVendas(2);
        Produto cleanCode = new Produto("Clean Code", "descricao clean code","LIVROS", new BigDecimal(70));
        cleanCode.setQtdDeVendas(6);

        //cliente
        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
                "cidade fake", "estado fake");
        Cliente bia = new Cliente("BIA", "11122233344", "999999999", endereco);
        Cliente carlos = new Cliente("CARLOS", "11122233344", "999999999", endereco);

        //pedidos
        pedidos = new ArrayList<>();
        pedidos.add(new Pedido("AUTOMOTIVA",jogoDePneu,carlos, new BigDecimal(1000),1, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA",jogoDePneu,bia, new BigDecimal(2000),2, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA",tapeteDeCarro,bia, new BigDecimal(400),2, LocalDate.now()));
        pedidos.add(new Pedido("LIVROS",cleanCode,bia, new BigDecimal(70),6, LocalDate.now()));

        produtosVendidos = new ArrayList<>();
        produtosVendidos.add(cleanCode);
        produtosVendidos.add(jogoDePneu);
        produtosVendidos.add(tapeteDeCarro);

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