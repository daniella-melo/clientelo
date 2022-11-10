package br.com.alura.clientelo.service;

import br.com.alura.clientelo.Main;
import br.com.alura.clientelo.estatisticas.CategoriaEstatistica;
import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class CategoriasService {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private List<Categoria> getCategoriasFromPedidos(List<Pedido> pedidos) {
        if (pedidos == null) return null;
        List<Categoria> listCategorias = new ArrayList<>();
        pedidos.stream().forEach(p -> listCategorias.add(new Categoria(p.getCategoria())));
        return listCategorias;
    }

    private List<CategoriaEstatistica> getEstatisticasPorCategoria(List<Categoria> categorias,List<Pedido> pedidos){
        int quantidade = 0;
        List<CategoriaEstatistica> estatisticasPorCategoria = new ArrayList<>();
        for (Categoria c: categorias) {
            if(estatisticasPorCategoria!=null){
                if (estatisticasPorCategoria.stream().filter(cat-> cat.getCategoria()
                        .getNome().equals(c.getNome())).findAny() != null) break;
            }
            BigDecimal montante = BigDecimal.ZERO;
            CategoriaEstatistica estatistica = new CategoriaEstatistica(c);

            estatistica.getPedidos().addAll(pedidos.stream().filter(p -> p.getCategoria().equals(c.getNome())).toList());
            montante = pedidos.stream().filter(p -> p.getCategoria().equals(c.getNome()))
                    .map(p -> p.getValorTotal()).reduce(montante, BigDecimal::add);
            quantidade = pedidos.stream().filter(p -> p.getCategoria().equals(c.getNome()))
                    .map(p -> p.getQuantidade()).reduce(quantidade, Integer::sum);

            estatistica.setMontanteDeVendas(montante);
            estatistica.setQtdDeVendas(quantidade);

        }
        return estatisticasPorCategoria;
    }

     public List<CategoriaEstatistica> vendasPorCategoria(List<Pedido> pedidos){
         List<Categoria> listCategorias = getCategoriasFromPedidos(pedidos);
         List<CategoriaEstatistica> estatisticas = getEstatisticasPorCategoria(listCategorias, pedidos);
         logDetalhesCategorias(estatisticas);
         return estatisticas;
     }

     public List<Produto> produtoMaisCaroPorCategoria(List<CategoriaEstatistica> categorias, List<Pedido> pedidos){
        List<Produto> listProdutos = new ArrayList<>();
         for (CategoriaEstatistica c : categorias){
             List<Pedido> pedidosDaCategoria = getPedidosFromCategoria(c.getCategoria(), pedidos);
             Produto produtoMaisCaro = new Produto();

             for (Pedido pedidoAtual : pedidosDaCategoria){
                 BigDecimal precoAtual = pedidoAtual.getPreco().divide(new BigDecimal(pedidoAtual.getQuantidade()), 2, RoundingMode.HALF_UP);
                 if (produtoMaisCaro == null || produtoMaisCaro.getPrecoUnitario() == null ||precoAtual.compareTo(produtoMaisCaro.getPrecoUnitario()) > 0 ) {
                     produtoMaisCaro = pedidoAtual.getProduto();
                     produtoMaisCaro.setPrecoUnitario(precoAtual);;
                 }
             }
             produtoMaisCaro.setCategoria(c.getCategoria().getNome());
             listProdutos.add(produtoMaisCaro);
             logProdutoMaisCaroPorCategoria(c.getCategoria().getNome(), produtoMaisCaro.getNome(), produtoMaisCaro.getPrecoUnitario());
         }
         return listProdutos;
     }

     private List<Pedido> getPedidosFromCategoria(Categoria c, List<Pedido> pedidos){
        List<Pedido> pedidosDaCategoria = new ArrayList<>();
        pedidosDaCategoria.addAll(pedidos.stream().filter(p -> p.getCategoria() == c.getNome()).toList());
        return pedidosDaCategoria;
     }

     private void logDetalhesCategorias(List<CategoriaEstatistica> categorias){
         if(categorias == null) return;
         for (CategoriaEstatistica categoria:categorias) {
             logger.info("CATEGORIA: {}",categoria.getCategoria());
             logger.info("QUANTIDADE: {}",categoria.getQtdDeVendas());
             logger.info("MONTANTE: {}",categoria.getMontanteDeVendas());
         }
     }

    private void logProdutoMaisCaroPorCategoria(String categoria, String produtoMaisCaro, BigDecimal precoProdutoMaisCaro){
         logger.info("CATEGORIA: {}",categoria);
         logger.info("PRODUTO: {}",produtoMaisCaro);
         logger.info("PRECO (1 unidade): {}",precoProdutoMaisCaro);
     }

}
