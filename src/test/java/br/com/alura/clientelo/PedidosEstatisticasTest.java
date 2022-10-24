package br.com.alura.clientelo;

import br.com.alura.clientelo.model.Pedido;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PedidosEstatisticasTest {

    private PedidosEstatisticas pedidosEstatisticas;

    @BeforeAll
    static void setup(){
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("AUTOMOTIVA","Jogo de pneus","CARLOS", new BigDecimal(1000),1, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA","Jogo de pneus","BIA", new BigDecimal(1000),2, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA","Tapete de carro","BIA", new BigDecimal(400),1, LocalDate.now()));
        pedidos.add(new Pedido("LIVROS","Clean Code","BIA", new BigDecimal(70),3, LocalDate.now()));
    }

    void deveriaRetornarLstaOrdenadaPorProdutoMaisVendido(){

    }
}