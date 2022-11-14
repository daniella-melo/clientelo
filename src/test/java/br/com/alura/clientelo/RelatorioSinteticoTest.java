package br.com.alura.clientelo;

import br.com.alura.clientelo.estatisticas.PedidoEstatistica;
import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Endereco;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
//import br.com.alura.clientelo.service.PedidosService;
import br.com.alura.clientelo.util.RelatorioSintetico;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RelatorioSinteticoTest {
//    private RelatorioSintetico relatorioSintetico;
//
//    private RelatorioSintetico actualRelatorioSintetico;
//    private PedidosService pedidosService;
//    private static Map<Pedido, PedidoEstatistica> mapPedidos;
//    private static PedidoEstatistica pedidoMaisBarato;
//    private static PedidoEstatistica pedidoMaisCaro;
//
//    @BeforeAll
//    static void setup(){
//        Produto jogoDePneu = new Produto();
//        jogoDePneu.setNome("Jogo de pneus");
//
//        Produto tapeteDeCarro = new Produto();
//        tapeteDeCarro.setNome("Tapete de carro");
//
//        Produto cleanCode = new Produto();
//        cleanCode.setNome("Clean Code");
//
//        //cliente
//        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
//                "cidade fake", "estado fake");
//        Cliente bia = new Cliente("BIA", "11122233344", "999999999", endereco);
//        Cliente carlos = new Cliente("CARLOS", "11122233344", "999999999", endereco);
//
//        //pedidos
//        mapPedidos = new HashMap<>();
//        Pedido pedido1 = new Pedido("AUTOMOTIVA",carlos,LocalDate.now());
//        PedidoEstatistica pedidoEstatistica1 = new PedidoEstatistica(pedido1, new BigDecimal(1000), jogoDePneu,1);
//        mapPedidos.put(pedido1,pedidoEstatistica1);
//
//        Pedido pedido2 = new Pedido("AUTOMOTIVA",bia, LocalDate.now());
//        PedidoEstatistica pedidoEstatistica2 = new PedidoEstatistica(pedido2, new BigDecimal(1000), jogoDePneu,2);
//        mapPedidos.put(pedido2,pedidoEstatistica2);
//
//        Pedido pedido3 = new Pedido("AUTOMOTIVA",bia, LocalDate.now());
//        PedidoEstatistica pedidoEstatistica3 = new PedidoEstatistica(pedido3, new BigDecimal(400), tapeteDeCarro,2);
//        mapPedidos.put(pedido3,pedidoEstatistica3);
//
//        Pedido pedido4 = new Pedido("LIVROS",bia, LocalDate.now());
//        PedidoEstatistica pedidoEstatistica4 = new PedidoEstatistica(pedido4, new BigDecimal(70), cleanCode,6);
//        mapPedidos.put(pedido4,pedidoEstatistica4);
//
//        pedidoMaisBarato = pedidoEstatistica4;
//        pedidoMaisCaro = pedidoEstatistica2;
//    }
//
//    @BeforeEach
//    void beforeEach() {
//        pedidosService = new PedidosService(mapPedidos);
//        relatorioSintetico = new RelatorioSintetico();
//        actualRelatorioSintetico = new RelatorioSintetico();
//        actualRelatorioSintetico = relatorioSintetico.getAll(mapPedidos);
//    }
//
//    @Test
//    void deveriaRetornarTotalDeProdutosVendidos(){
//        assertEquals(11, actualRelatorioSintetico.getTotalDeProdutosVendidos());
//    }
//
//    @Test
//    void deveriaRetornarTotalDePedidosRealizados(){
//        assertEquals(4, actualRelatorioSintetico.getTotalDePedidosRealizados());
//    }
//
//    @Test
//    void deveriaRetornarMontanteDeVendas(){
//        assertEquals(new BigDecimal(4220), actualRelatorioSintetico.getMontanteDeVendas());
//    }
//
//    @Test
//    void deveriaRetornarPedidoMaisBarato(){
//        assertTrue(pedidoMaisBarato.getPreco() == actualRelatorioSintetico.getPedidoMaisBarato().getPreco());
//    }
//
//    @Test
//    void deveriaRetornarPedidoMaisCaro(){
//        assertTrue(pedidoMaisCaro.getPreco() == actualRelatorioSintetico.getPedidoMaisCaro().getPreco());
//    }
//
//    @Test
//    void deveriaRetornarTotalDeCategorias(){
//        assertEquals(2, actualRelatorioSintetico.getTotalDeCategorias());
//    }
}
