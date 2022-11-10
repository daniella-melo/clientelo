package br.com.alura.clientelo;

import br.com.alura.clientelo.estatisticas.CategoriaEstatistica;
import br.com.alura.clientelo.estatisticas.PedidoEstatistica;
import br.com.alura.clientelo.model.*;
import br.com.alura.clientelo.service.CategoriasService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriasServiceTest {
    private CategoriasService categoriasService;
    private static Map<Pedido, PedidoEstatistica> mapPedidos;
    private static List<CategoriaEstatistica> categoriasEstatisticas;
    private static Produto produtoMaisCaroAutomotiva;
    private static Produto produtoMaisCaroLivros;
    @BeforeAll
    public static void setup(){
        //TODO: verificas possibilidade de diminuir a quantidade de código aqui
        //produtos
        Produto jogoDePneu = new Produto("Jogo de pneus","conjunto de jogo de pneu","AUTOMOTIVA", new BigDecimal(1000).setScale(2, RoundingMode.HALF_UP) );
        Produto tapeteDeCarro = new Produto("Tapete de carro","descricao tapete de carro","AUTOMOTIVA", new BigDecimal(200) );
        Produto cleanCode = new Produto("Clean Code", "livro clean code", "LIVROS" ,new BigDecimal(100).setScale(2, RoundingMode.HALF_UP));

        //cliente
        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
                "cidade fake", "estado fake");
        Cliente bia = new Cliente("BIA", "11122233344", "999999999", endereco);
        Cliente carlos = new Cliente("CARLOS", "11122233344", "999999999", endereco);

        //pedidos
        mapPedidos = new HashMap<>();
        Pedido pedido1 = new Pedido("AUTOMOTIVA",carlos,LocalDate.now());
        PedidoEstatistica pedidoEstatistica1 = new PedidoEstatistica(pedido1, new BigDecimal(1000), jogoDePneu,1);
        mapPedidos.put(pedido1,pedidoEstatistica1);

        Pedido pedido2 = new Pedido("AUTOMOTIVA",bia, LocalDate.now());
        PedidoEstatistica pedidoEstatistica2 = new PedidoEstatistica(pedido2, new BigDecimal(1000), jogoDePneu,2);
        mapPedidos.put(pedido2,pedidoEstatistica2);

        Pedido pedido3 = new Pedido("AUTOMOTIVA",bia, LocalDate.now());
        PedidoEstatistica pedidoEstatistica3 = new PedidoEstatistica(pedido3, new BigDecimal(400), tapeteDeCarro,2);
        mapPedidos.put(pedido3,pedidoEstatistica3);

        Pedido pedido4 = new Pedido("LIVROS",bia, LocalDate.of(2022,1,1));
        PedidoEstatistica pedidoEstatistica4 = new PedidoEstatistica(pedido4, new BigDecimal(70), cleanCode,6);
        mapPedidos.put(pedido4,pedidoEstatistica4);

        produtoMaisCaroAutomotiva = jogoDePneu;

        Categoria livros = new Categoria("LIVROS");
        Categoria automotiva = new Categoria("AUTOMOTIVA");

        categoriasEstatisticas = new ArrayList<>();
        categoriasEstatisticas.add(new CategoriaEstatistica(livros));
        categoriasEstatisticas.get(0).setQtdDeVendas(6);
        categoriasEstatisticas.get(0).setMontanteDeVendas(new BigDecimal(420));

        categoriasEstatisticas.add(new CategoriaEstatistica(automotiva));
        categoriasEstatisticas.get(1).setQtdDeVendas(5);
        categoriasEstatisticas.get(1).setMontanteDeVendas(new BigDecimal(3800));


        produtoMaisCaroLivros = cleanCode;
    }

    @BeforeEach
    void beforeEach() {
        categoriasService = new CategoriasService();
    }


    @Test
    void deveriaRetonarListaDeCategoriasValidaDeVendasPorCategoria(){
        List<CategoriaEstatistica> actuallistCategorias = categoriasService.vendasPorCategoria(mapPedidos);
        actuallistCategorias.sort(Comparator.comparing(o -> o.getCategoria().getNome()));
        categoriasEstatisticas.sort(Comparator.comparing(o -> o.getCategoria().getNome()));

        for (int i = 0; i < actuallistCategorias.size(); i++){
            assertEquals(categoriasEstatisticas.get(i).getCategoria().getNome(), actuallistCategorias.get(i).getCategoria().getNome());
            assertEquals(categoriasEstatisticas.get(i).getMontanteDeVendas(), actuallistCategorias.get(i).getMontanteDeVendas());
            assertEquals(categoriasEstatisticas.get(i).getQtdDeVendas(), actuallistCategorias.get(i).getQtdDeVendas());
            //assertTrue(categorias.get(i).getPedidos().containsAll(actuallistCategorias.get(i).getPedidos()));
        }
    }

    @Test
    void deveriaRetornarProdutoMaisCaroPorCategoria(){
        List<Produto> actualProdutoMaisCaro = categoriasService.produtoMaisCaroPorCategoria(categoriasEstatisticas, mapPedidos);

        assertEquals(produtoMaisCaroLivros.getNome(), actualProdutoMaisCaro.get(0).getNome());
        assertEquals(produtoMaisCaroLivros.getCategoria(),actualProdutoMaisCaro.get(0).getCategoria());
        assertEquals(produtoMaisCaroLivros.getPrecoUnitario(), actualProdutoMaisCaro.get(0).getPrecoUnitario());

        assertEquals(produtoMaisCaroAutomotiva.getNome(), actualProdutoMaisCaro.get(1).getNome());
        assertEquals(produtoMaisCaroAutomotiva.getCategoria(),actualProdutoMaisCaro.get(1).getCategoria());
        assertEquals(produtoMaisCaroAutomotiva.getPrecoUnitario(), actualProdutoMaisCaro.get(1).getPrecoUnitario());


    }
}