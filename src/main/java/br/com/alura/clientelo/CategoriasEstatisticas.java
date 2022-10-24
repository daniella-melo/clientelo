package br.com.alura.clientelo;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Pedido;
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

    private HashMap<Categoria, ArrayList<Pedido>> montarCategorias(List<Pedido> pedidos) {
        HashMap<Categoria, ArrayList<Pedido>> mapPedidosPorCategoria = new HashMap<>();
        Set<String> categorias = getCategoriasFromPedidos(pedidos);
        List<Categoria> listCategorias = new ArrayList<>();

        if (categorias != null) {
            ArrayList<Pedido> pedidosDestaCategoria = new ArrayList<>();
            int quantidade = 0;
            BigDecimal montante = BigDecimal.ZERO;

            for (String categoria : categorias) {
                pedidosDestaCategoria.addAll(pedidos.stream().filter(p -> p.getCategoria().equals(categoria)).toList());
                montante = pedidos.stream().filter(p -> p.getCategoria().equals(categoria))
                        .map(p -> p.getValorTotal()).reduce(montante, BigDecimal::add);
                quantidade = pedidos.stream().filter(p -> p.getCategoria().equals(categoria))
                        .map(p -> p.getQuantidade()).reduce(quantidade, Integer::sum);

                Categoria newCategoria = new Categoria(categoria, montante, quantidade);
                newCategoria.getPedidos().addAll(pedidosDestaCategoria);
                listCategorias.add(newCategoria);

                mapPedidosPorCategoria.put(newCategoria, pedidosDestaCategoria);
            }
        }
        return mapPedidosPorCategoria;
    }

     public void vendasPorCategoria(List<Pedido> pedidos){
         HashMap<Categoria, ArrayList<Pedido>> mapPedidosPorCategoria = montarCategorias(pedidos);
         List<Categoria> categorias = new ArrayList<>();
         for (Map.Entry<Categoria, ArrayList<Pedido>> pair : mapPedidosPorCategoria.entrySet()) {
            categorias.add(pair.getKey());
         }
         logDetalhesCategorias(categorias);
     }

     public void produtoMaisCaroPorCategoria(HashMap<Categoria, ArrayList<Pedido>> map){
         for (Map.Entry<Categoria, ArrayList<Pedido>> pair : map.entrySet()){
             List<Pedido> pedidos = pair.getValue();
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
             logProdutoMaisCaroPorCategoria(pair.getKey().getNome(), produtoMaisCaro, precoProdutoMaisCaro);
         }
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
