package br.com.alura.clientelo;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Pedido;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriasEstatisticasTest {
    private CategoriasEstatisticas categoriasEstatisticas;
    private static List<Pedido> pedidos;
    private static List<Categoria> categorias;

    @BeforeAll
    public static void setup(){
        //respeitando a ordem alfabetica ao inserir na lista de categorias
        pedidos = new ArrayList<>();
        pedidos.add(new Pedido("AUTOMOTIVA","Jogo de pneus","CARLOS", new BigDecimal(1000),1, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA","Jogo de pneus","BIA", new BigDecimal(1000),2, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA","Tapete de carro","BIA", new BigDecimal(400),2, LocalDate.now()));

        categorias = new ArrayList<>();
        categorias.add(new Categoria("AUTOMOTIVA", new BigDecimal(3800), 5));
        categorias.get(0).getPedidos().addAll(pedidos);

        pedidos.add(new Pedido("LIVROS","Clean Code","BIA", new BigDecimal(70),6, LocalDate.now()));
        categorias.add(new Categoria("LIVROS", new BigDecimal(420), 6));
        categorias.get(1).getPedidos().add(pedidos.get(2));

    }

    @BeforeEach
    void beforeEach() {
        categoriasEstatisticas = new CategoriasEstatisticas();
    }


    @Test
    void deveriaRetonarListaDeCategoriasValida(){
        List<Categoria> actuallistCategorias = categoriasEstatisticas.vendasPorCategoria(pedidos);
        actuallistCategorias.sort(new Comparator<Categoria>() {
            @Override
            public int compare(Categoria c1, Categoria c2) {
                return c1.getNome().compareTo(c2.getNome());
            }
        });

        for (int i = 0; i < actuallistCategorias.size(); i++){
            assertEquals(categorias.get(i).getNome(), actuallistCategorias.get(i).getNome());
            assertEquals(categorias.get(i).getMontanteDeVendas(), actuallistCategorias.get(i).getMontanteDeVendas());
            assertEquals(categorias.get(i).getQtdDeVendas(), actuallistCategorias.get(i).getQtdDeVendas());
            assertTrue(categorias.get(i).getPedidos().containsAll(actuallistCategorias.get(i).getPedidos()));
        }
    }
}