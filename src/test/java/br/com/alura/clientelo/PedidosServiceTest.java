package br.com.alura.clientelo;

import br.com.alura.clientelo.estatisticas.PedidoEstatistica;
import br.com.alura.clientelo.model.*;
//import br.com.alura.clientelo.service.PedidosService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PedidosServiceTest {
//
//    private PedidosService pedidosService;
//
//    private static Map<Pedido, PedidoEstatistica> mapPedidos;
//    private static List<Pedido> pedidos;
//    private static List<Produto> produtosVendidos;
//    @BeforeAll
//    static void setup(){
//        //produtos
//        Produto jogoDePneu = new Produto("Jogo de pneus", "descricao jogo de pneus","AUTOMOTIVA", new BigDecimal(1000) );
//        jogoDePneu.setQtdDeVendas(3);
//        Produto tapeteDeCarro = new Produto("Tapete de carro","descricao tapete de carro","AUTOMOTIVA", new BigDecimal(200) );
//        tapeteDeCarro.setQtdDeVendas(2);
//        Produto cleanCode = new Produto("Clean Code", "descricao clean code","LIVROS", new BigDecimal(70));
//        cleanCode.setQtdDeVendas(6);
//
//        //cliente
//        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
//                "cidade fake", "estado fake");
//        Cliente bia = new Cliente("BIA", "11122233344", "999999999", endereco);
//        Cliente carlos = new Cliente("CARLOS", "11122233344", "999999999", endereco);
//
//        //pedidos
//        mapPedidos = new HashMap<>();
//
//        Pedido pedido1 = new Pedido("AUTOMOTIVA",carlos,LocalDate.now());
//        PedidoEstatistica pedidoEstatistica1 = new PedidoEstatistica(pedido1, new BigDecimal(1000), jogoDePneu,1);
//        mapPedidos.put(pedido1,pedidoEstatistica1);
//
//        Pedido pedido2 = new Pedido("AUTOMOTIVA",bia, LocalDate.now());
//        PedidoEstatistica pedidoEstatistica2 = new PedidoEstatistica(pedido2, new BigDecimal(1000), jogoDePneu,2);
//        mapPedidos.put(pedido1,pedidoEstatistica2);
//
//        Pedido pedido3 = new Pedido("AUTOMOTIVA",bia, LocalDate.now());
//        PedidoEstatistica pedidoEstatistica3 = new PedidoEstatistica(pedido3, new BigDecimal(400), tapeteDeCarro,2);
//        mapPedidos.put(pedido1,pedidoEstatistica3);
//
//        Pedido pedido4 = new Pedido("LIVROS",bia, LocalDate.now());
//        PedidoEstatistica pedidoEstatistica4 = new PedidoEstatistica(pedido4, new BigDecimal(70), cleanCode,6);
//        mapPedidos.put(pedido1,pedidoEstatistica4);
//
//        produtosVendidos = new ArrayList<>();
//        produtosVendidos.add(cleanCode);
//        produtosVendidos.add(jogoDePneu);
//        produtosVendidos.add(tapeteDeCarro);
//
//    }
//
//    @BeforeEach
//    void beforeEach() {
//        pedidosService = new PedidosService(mapPedidos);
//    }
//
//    @Test
//    void deveriaRetornarListaOrdenadaPorProdutoMaisVendido() {
//        List<Produto> actualListProdutos = pedidosService.produtoMaisVendidos();
//
//        for (int i = 0; i < actualListProdutos.size(); i++){
//            assertEquals(produtosVendidos.get(i).getNome(), actualListProdutos.get(i).getNome());
//            assertEquals(produtosVendidos.get(i).getQtdDeVendas(), actualListProdutos.get(i).getQtdDeVendas());
//            assertEquals(produtosVendidos.get(i).getCategoria(), actualListProdutos.get(i).getCategoria());
//        }
//    }
//
//    @Test
//    void metodoDeProdutosMaisVendidosDeveriaRetornarNullCasoListaDePedidosSejaNull(){
//        pedidosService = new PedidosService(null);
//        List<Produto> listProdutos = pedidosService.produtoMaisVendidos();
//        assertEquals(null, listProdutos);
//    }
}