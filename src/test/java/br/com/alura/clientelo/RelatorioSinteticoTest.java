package br.com.alura.clientelo;

import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.service.PedidosService;
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
    private ValoresGerais valoresGerais;

    private ValoresGerais actualValoresGerais;
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
        valoresGerais = new ValoresGerais();
        actualValoresGerais = new ValoresGerais();
        actualValoresGerais = valoresGerais.getAll(pedidos);
    }
    
    @Test
    void deveriaRetornarTotalDeProdutosVendidos(){
        assertEquals(11, actualValoresGerais.getTotalDeProdutosVendidos());
    }

    @Test
    void deveriaRetornarTotalDePedidosRealizados(){
        assertEquals(4, actualValoresGerais.getTotalDePedidosRealizados());
    }

    @Test
    void deveriaRetornarMontanteDeVendas(){
        assertEquals(new BigDecimal(4220), actualValoresGerais.getMontanteDeVendas());
    }

    @Test
    void deveriaRetornarPedidoMaisBarato(){
        assertTrue(pedidoMaisBarato.equals(actualValoresGerais.getPedidoMaisBarato()));
    }

    @Test
    void deveriaRetornarPedidoMaisCaro(){
        assertTrue(pedidoMaisCaro.equals(actualValoresGerais.getPedidoMaisCaro()));
    }

    @Test
    void deveriaRetornarTotalDeCategorias(){
        assertEquals(2, actualValoresGerais.getTotalDeCategorias());
    }
}
