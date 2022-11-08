package br.com.alura.clientelo;

import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.service.PedidosService;
import br.com.alura.clientelo.util.RelatorioSintetico;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RelatorioSinteticoTest {
    private RelatorioSintetico relatorioSintetico;

    private RelatorioSintetico actualRelatorioSintetico;
    private PedidosService pedidosService;
    private static List<Pedido> pedidos;

    private static Pedido pedidoMaisBarato;
    private static Pedido pedidoMaisCaro;

    @BeforeAll
    static void setup(){
        pedidos = new ArrayList<>();
        pedidos.add(new Pedido("AUTOMOTIVA","Jogo de pneus","CARLOS", new BigDecimal(1000),1, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA","Jogo de pneus","BIA", new BigDecimal(1000),2, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA","Tapete de carro","BIA", new BigDecimal(400),2, LocalDate.now()));
        pedidos.add(new Pedido("LIVROS","Clean Code","BIA", new BigDecimal(70),6, LocalDate.now()));

        pedidoMaisBarato = pedidos.get(3);
        pedidoMaisCaro = pedidos.get(1);
    }

    @BeforeEach
    void beforeEach() {
        pedidosService = new PedidosService(pedidos);
        relatorioSintetico = new RelatorioSintetico();
        actualRelatorioSintetico = new RelatorioSintetico();
        actualRelatorioSintetico = relatorioSintetico.getAll(pedidos);
    }
    
    @Test
    void deveriaRetornarTotalDeProdutosVendidos(){
        assertEquals(11, actualRelatorioSintetico.getTotalDeProdutosVendidos());
    }

    @Test
    void deveriaRetornarTotalDePedidosRealizados(){
        assertEquals(4, actualRelatorioSintetico.getTotalDePedidosRealizados());
    }

    @Test
    void deveriaRetornarMontanteDeVendas(){
        assertEquals(new BigDecimal(4220), actualRelatorioSintetico.getMontanteDeVendas());
    }

    @Test
    void deveriaRetornarPedidoMaisBarato(){
        assertTrue(pedidoMaisBarato.equals(actualRelatorioSintetico.getPedidoMaisBarato()));
    }

    @Test
    void deveriaRetornarPedidoMaisCaro(){
        assertTrue(pedidoMaisCaro.equals(actualRelatorioSintetico.getPedidoMaisCaro()));
    }

    @Test
    void deveriaRetornarTotalDeCategorias(){
        assertEquals(2, actualRelatorioSintetico.getTotalDeCategorias());
    }
}
