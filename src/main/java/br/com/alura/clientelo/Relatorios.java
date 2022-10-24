package br.com.alura.clientelo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Relatorios {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public void produtoMaisVendidos(List<Pedido> pedidos){
        List<Produto> listProdutos = new ArrayList<>();
        Set<String> produtos = new HashSet<>();
        for (Pedido pedido: pedidos) {
            if(pedido == null) break;
            produtos.add(pedido.getProduto());
        }
        
        for (String produto: produtos) {
            int quantidade = 0;
            String categoria = null;
            BigDecimal montante = BigDecimal.ZERO;

            quantidade = pedidos.stream().filter(p -> p.getProduto().equals(produto)).map(p->p.getQuantidade()).reduce(quantidade, Integer::sum);
            categoria = pedidos.stream().filter(p -> p.getProduto().equals(produto)).map(p -> p.getCategoria()).findFirst().get();

            Produto newProduto = new Produto(produto, categoria, quantidade);
            listProdutos.add(newProduto);
        }

        listProdutos.sort(Comparator.comparing(Produto::getQtdDeVendas).reversed());
        for (Produto produto : listProdutos) {
            logger.info("PRODUTO: {}",produto.getNome());
            logger.info("QUANTIDADE: {}", produto.getQtdDeVendas());
        }
    }

    public HashMap<String, ArrayList<Pedido>> vendasPorCategoria(List<Pedido> pedidos, int totalCategorias){
        Set<String> categorias = new HashSet<>();
        HashMap<String, ArrayList<Pedido>> mapPedidosPorCategoria = new HashMap<>();

        for (Pedido pedido: pedidos) {
            if(pedido == null) break;
            categorias.add(pedido.getCategoria());
        }

        for (String categoria: categorias) {
            int quantidade = 0;
            BigDecimal montante = BigDecimal.ZERO;
            ArrayList<Pedido> pedidosDestaCategoria = new ArrayList<>();

            pedidosDestaCategoria.addAll(pedidos.stream().filter(p -> p.getCategoria().equals(categoria)).toList());
            montante = pedidos.stream().filter(p -> p.getCategoria().equals(categoria)).map(p -> p.getValorTotal()).reduce(montante, BigDecimal::add);
            quantidade = pedidos.stream().filter(p -> p.getCategoria().equals(categoria)).map(p-> p.getQuantidade()).reduce(quantidade, Integer::sum);

            mapPedidosPorCategoria.put(categoria, pedidosDestaCategoria);
            logger.info("CATEGORIA: {}",categoria);
            logger.info("QUANTIDADE: {}",quantidade);
            logger.info("MONTANTE: {}",montante);

        }
        return mapPedidosPorCategoria;
    }

    public void produtoMaisCaroPorCategoria(HashMap<String, ArrayList<Pedido>> map){
        for (Map.Entry<String, ArrayList<Pedido>> pair : map.entrySet()){
            List<Pedido> pedidos = pair.getValue();
            String[] produtosProcessados = new String[20];
            String produtoMaisCaro = null;
            BigDecimal precoProdutoMaisCaro = BigDecimal.ZERO;

            for (Pedido pedidoAtual : pedidos){
                    BigDecimal precoAtual = pedidoAtual.getPreco().divide(new BigDecimal(pedidoAtual.getQuantidade()), 2, RoundingMode.HALF_UP);
                    if (produtoMaisCaro == null || precoAtual.compareTo(precoProdutoMaisCaro) == 1 ) {
                        produtoMaisCaro = pedidoAtual.getProduto();
                        precoProdutoMaisCaro = precoAtual;
                    }
            }
            logger.info("CATEGORIA: {}",pair.getKey());
            logger.info("PRODUTO: {}",produtoMaisCaro);
            logger.info("PRECO (1 unidade): {}",precoProdutoMaisCaro);

        }
    }
}
