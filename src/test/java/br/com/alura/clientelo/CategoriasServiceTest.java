package br.com.alura.clientelo;

import br.com.alura.clientelo.model.*;
import br.com.alura.clientelo.service.CategoriasService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriasServiceTest {
    private CategoriasService categoriasService;
    private static List<Pedido> pedidos;
    private static List<Categoria> categorias;
    private static Produto produtoMaisCaroAutomotiva;
    private static Produto produtoMaisCaroLivros;
    @BeforeAll
    public static void setup(){

        //produtos
        Produto jogoDePneu = new Produto("Jogo de pneus","conjunto de jogo de pneu","AUTOMOTIVA", new BigDecimal(1000).setScale(2, RoundingMode.HALF_UP) );
        Produto tapeteDeCarro = new Produto("Tapete de carro","descricao tapete de carro","AUTOMOTIVA", new BigDecimal(200) );
        Produto cleanCode = new Produto("Clean Code", "livro clean code", "LIVROS" ,new BigDecimal(100).setScale(2, RoundingMode.HALF_UP));

        //cliente
        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
                "cidade fake", "estado fake");
        Cliente bia = new Cliente("BIA", "11122233344", "999999999", endereco);
        Cliente carlos = new Cliente("CARLOS", "11122233344", "999999999", endereco);

        //respeitando a ordem alfabetica ao inserir na lista de categorias
        pedidos = new ArrayList<>();
        pedidos.add(new Pedido("AUTOMOTIVA",jogoDePneu,carlos, new BigDecimal(1000).setScale(2, RoundingMode.HALF_UP),1, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA",jogoDePneu,bia, new BigDecimal(1000).setScale(2, RoundingMode.HALF_UP),2, LocalDate.now()));
        pedidos.add(new Pedido("AUTOMOTIVA",tapeteDeCarro,bia, new BigDecimal(400).setScale(2, RoundingMode.HALF_UP),2, LocalDate.now()));

        produtoMaisCaroAutomotiva = jogoDePneu;

        categorias = new ArrayList<>();
        categorias.add(new Categoria("AUTOMOTIVA", new BigDecimal(3800).setScale(2, RoundingMode.HALF_UP), 5));
        categorias.get(0).getPedidos().add(new Pedido("AUTOMOTIVA",jogoDePneu,carlos, new BigDecimal(1000).setScale(2, RoundingMode.HALF_UP),1, LocalDate.now()));
        categorias.get(0).getPedidos().add(new Pedido("AUTOMOTIVA",jogoDePneu,bia, new BigDecimal(1000).setScale(2, RoundingMode.HALF_UP),2, LocalDate.now()));
        categorias.get(0).getPedidos().add(new Pedido("AUTOMOTIVA",tapeteDeCarro,bia, new BigDecimal(400).setScale(2, RoundingMode.HALF_UP),2, LocalDate.now()));


        categorias.add(new Categoria("LIVROS", new BigDecimal(600).setScale(2, RoundingMode.HALF_UP), 6));
        categorias.get(1).getPedidos().add(new Pedido("LIVROS",cleanCode,bia, new BigDecimal(600).setScale(2, RoundingMode.HALF_UP),6, LocalDate.now()));
        produtoMaisCaroLivros = cleanCode;
    }

    @BeforeEach
    void beforeEach() {
        categoriasService = new CategoriasService();
    }


    @Test
    void deveriaRetonarListaDeCategoriasValidaDeVendasPorCategoria(){
        List<Categoria> actuallistCategorias = categoriasService.vendasPorCategoria(pedidos);
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
            //assertTrue(categorias.get(i).getPedidos().containsAll(actuallistCategorias.get(i).getPedidos()));
        }
    }

    @Test
    void deveriaRetornarProdutoMaisCaroPorCategoria(){
        List<Produto> actualProdutoMaisCaro = categoriasService.produtoMaisCaroPorCategoria(categorias);

        assertEquals(produtoMaisCaroAutomotiva.getNome(), actualProdutoMaisCaro.get(0).getNome());
        assertEquals(produtoMaisCaroAutomotiva.getCategoria(),actualProdutoMaisCaro.get(0).getCategoria());
        assertEquals(produtoMaisCaroAutomotiva.getPrecoUnitario(), actualProdutoMaisCaro.get(0).getPrecoUnitario());

        assertEquals(produtoMaisCaroLivros.getNome(), actualProdutoMaisCaro.get(1).getNome());
        assertEquals(produtoMaisCaroLivros.getCategoria(),actualProdutoMaisCaro.get(1).getCategoria());
        assertEquals(produtoMaisCaroLivros.getPrecoUnitario(), actualProdutoMaisCaro.get(1).getPrecoUnitario());
    }
}