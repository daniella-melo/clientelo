package br.com.alura.clientelo;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class CategoriasEstatisticas {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private Set<String> getCategoriasFromPedidos(List<Pedido> pedidos) {
        if (pedidos == null) return null;
        Set<String> categorias = new HashSet<>();
        pedidos.stream().forEach(p -> categorias.add(p.getCategoria()));
        return categorias;
    }

    public List<Categoria> montarCategoria(Set<String> categorias, List<Pedido> pedidos){
        List<Categoria> listCategorias = new ArrayList<>();
        for (String categoria : categorias) {
            ArrayList<Pedido> pedidosDestaCategoria = new ArrayList<>();
            int quantidade = 0;
            BigDecimal montante = BigDecimal.ZERO;

            pedidosDestaCategoria.addAll(pedidos.stream().filter(p -> p.getCategoria().equals(categoria)).toList());
            montante = pedidos.stream().filter(p -> p.getCategoria().equals(categoria))
                    .map(p -> p.getValorTotal()).reduce(montante, BigDecimal::add);
            quantidade = pedidos.stream().filter(p -> p.getCategoria().equals(categoria))
                    .map(p -> p.getQuantidade()).reduce(quantidade, Integer::sum);

            Categoria newCategoria = new Categoria(categoria, montante, quantidade);
            newCategoria.getPedidos().addAll(pedidosDestaCategoria);
            listCategorias.add(newCategoria);
        }
        return  listCategorias;
    }

     public List<Categoria> vendasPorCategoria(List<Pedido> pedidos){
         Set<String> categorias = getCategoriasFromPedidos(pedidos);
         List<Categoria> listCategorias = montarCategoria(categorias, pedidos);
         logDetalhesCategorias(listCategorias);
         return listCategorias;
     }

     public List<Produto> produtoMaisCaroPorCategoria(List<Categoria> categorias){
         for (Categoria c : categorias){
             List<Pedido> pedidos = c.getPedidos();
             String[] produtosProcessados = new String[20];
             String produtoMaisCaro = null;
             BigDecimal precoProdutoMaisCaro = BigDecimal.ZERO;

             for (Pedido pedidoAtual : pedidos){
                 BigDecimal precoAtual = pedidoAtual.getPreco().divide(new BigDecimal(pedidoAtual.getQuantidade()), 2, RoundingMode.HALF_UP);
                 if (produtoMaisCaro == null || precoAtual.compareTo(precoProdutoMaisCaro) > 0 ) {
                     produtoMaisCaro = pedidoAtual.getProduto();
                     precoProdutoMaisCaro = precoAtual;
                 }
             }
             logProdutoMaisCaroPorCategoria(c.getNome(), produtoMaisCaro, precoProdutoMaisCaro);
         }
         return null;
     }

     public void logDetalhesCategorias(List<Categoria> categorias){
         if(categorias == null) return;
         categorias.stream().forEach(c -> logger.info("CATEGORIA: {}",c.getNome()));
         categorias.stream().forEach(c -> logger.info("QUANTIDADE: {}",c.getQtdDeVendas()));
         categorias.stream().forEach(c -> logger.info("MONTANTE: {}",c.getMontanteDeVendas()));
     }

     public void logProdutoMaisCaroPorCategoria(String categoria, String produtoMaisCaro, BigDecimal precoProdutoMaisCaro){
         logger.info("CATEGORIA: {}",categoria);
         logger.info("PRODUTO: {}",produtoMaisCaro);
         logger.info("PRECO (1 unidade): {}",precoProdutoMaisCaro);
     }
}
