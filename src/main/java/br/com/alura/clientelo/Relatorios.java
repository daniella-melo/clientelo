package br.com.alura.clientelo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Relatorios {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public void maisVendidos(List<Pedido> pedidos){
        pedidos.sort(Comparator.comparing(Pedido::getQuantidade).reversed());

        for (Pedido pedido : pedidos) {
            logger.info("PRODUTO: {}",pedido.getProduto());
            logger.info("QUANTIDADE: {}", pedido.getQuantidade());
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
            for (Pedido pedido: pedidos) {
                if(pedido == null) break;
                if(pedido.getCategoria() == categoria){
                    pedidosDestaCategoria.add(pedido);
                    quantidade += pedido.getQuantidade();
                    montante = montante.add(pedido.getValorTotal());
                }
            }

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
                boolean jahProcessouProduto = false;

                for (int j = 0; j < produtosProcessados.length; j++) {
                    if (pedidoAtual.getProduto().equalsIgnoreCase(produtosProcessados[j])) {
                        jahProcessouProduto = true;
                    }
                }

                if(!jahProcessouProduto){
                    BigDecimal precoAtual = pedidoAtual.getPreco().divide(new BigDecimal(pedidoAtual.getQuantidade()), 2, RoundingMode.HALF_UP);
                    if (produtoMaisCaro == null || precoProdutoMaisCaro.compareTo(precoAtual) == 1) {
                        produtoMaisCaro = pedidoAtual.getProduto();
                        precoProdutoMaisCaro = precoAtual;
                    }
                }
            }
            logger.info("CATEGORIA: {}",pair.getKey());
            logger.info("PRODUTO: {}",produtoMaisCaro);
            logger.info("PRECO (1 unidade): {}",precoProdutoMaisCaro);

        }
    }
}
