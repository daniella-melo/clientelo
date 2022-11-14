package br.com.alura.clientelo.service;

import br.com.alura.clientelo.Main;
import br.com.alura.clientelo.estatisticas.CategoriaEstatistica;
import br.com.alura.clientelo.estatisticas.PedidoEstatistica;
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

    //TODO: refatorar os método para atenderem à nova modelagem
    private Set<Categoria> getCategoriasFromPedidos(List<Pedido> pedidos) {
        if (pedidos == null) return null;
        Set<Categoria> setCategorias = new HashSet<>();
        pedidos.stream().forEach(p -> setCategorias.add(new Categoria(p.getCategoria())));
        return setCategorias;
    }

    private List<CategoriaEstatistica> getEstatisticasPorCategoria(List<Categoria> categorias,Map<Pedido, PedidoEstatistica> mapPedidos){

        List<CategoriaEstatistica> estatisticasPorCategoria = new ArrayList<>();
        for (Categoria c: categorias) {
            if(estatisticasPorCategoria.size() != 0 && estatisticasPorCategoria.stream().filter(cat -> cat.getCategoria()
                    .getNome().equals(c.getNome())).findAny().isPresent()) {
                continue;
            }
            BigDecimal montante = BigDecimal.ZERO;
            CategoriaEstatistica estatistica = new CategoriaEstatistica(c);
            int quantidade = 0;

            List<PedidoEstatistica> pedidoEstatisticas = mapPedidos.values().stream().filter(p -> p.getPedido().getCategoria().equals(c.getNome())).toList();
            estatistica.getPedidos().addAll(mapPedidos.keySet());
            montante = pedidoEstatisticas.stream().map(p -> p.getValorTotal()).reduce(montante, BigDecimal::add);
            quantidade = pedidoEstatisticas.stream()
                    .map(p -> p.getQuantidade()).reduce(quantidade, Integer::sum);

            estatistica.setMontanteDeVendas(montante);
            estatistica.setQtdDeVendas(quantidade);

            estatisticasPorCategoria.add(estatistica);
        }
        return estatisticasPorCategoria;
        return null;
    }

     public List<CategoriaEstatistica> vendasPorCategoria(Map<Pedido, PedidoEstatistica> mapPedidos){
         Set<Categoria> setCategorias = getCategoriasFromPedidos(mapPedidos.keySet().stream().toList());
         List<Categoria> listCategorias = new ArrayList<>();
         setCategorias.stream().forEach(c->listCategorias.add(c));
         List<CategoriaEstatistica> estatisticas = getEstatisticasPorCategoria(listCategorias, mapPedidos);
         logDetalhesCategorias(estatisticas);
         return estatisticas;
     }

     public List<Produto> produtoMaisCaroPorCategoria(List<CategoriaEstatistica> categorias, Map<Pedido, PedidoEstatistica> mapPedidos){
        List<Produto> listProdutos = new ArrayList<>();
         for (CategoriaEstatistica c : categorias){
             List<PedidoEstatistica> pedidosDaCategoria = getPedidosFromCategoria(c.getCategoria(), mapPedidos);
             Produto produtoMaisCaro = new Produto();

             for (PedidoEstatistica pedidoAtual : pedidosDaCategoria){
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

     private List<PedidoEstatistica> getPedidosFromCategoria(Categoria c, Map<Pedido, PedidoEstatistica> mapPedidos){
        List<PedidoEstatistica> pedidosDaCategoria = new ArrayList<>();
        pedidosDaCategoria.addAll(mapPedidos.values().stream().filter(p -> p.getPedido().getCategoria().equals(c.getNome())).toList());
        return pedidosDaCategoria;
     }

     private void logDetalhesCategorias(List<CategoriaEstatistica> categorias){
         if(categorias == null) return;
         for (CategoriaEstatistica categoria:categorias) {
             logger.info("CATEGORIA: {}",categoria.getCategoria().getNome());
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
