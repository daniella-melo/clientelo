package br.com.alura.clientelo.service;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Pedido;

import java.math.BigDecimal;
import java.util.*;

public class CategoriaService {

    private Set<String> getCategoriasFromPedidos(List<Pedido> pedidos) {
        if (pedidos == null) return null;
        Set<String> categorias = new HashSet<>();
        for (Pedido pedido : pedidos) {
            if (pedido == null) break;
            categorias.add(pedido.getCategoria());
        }
        return categorias;
    }

    private HashMap<String, ArrayList<Pedido>> montarCategorias(List<Pedido> pedidos) {
        HashMap<String, ArrayList<Pedido>> mapPedidosPorCategoria = new HashMap<>();
        Set<String> categorias = getCategoriasFromPedidos(pedidos);
        if (categorias != null) {
            ArrayList<Pedido> pedidosDestaCategoria = new ArrayList<>();
            int quantidade = 0;
            BigDecimal montante = BigDecimal.ZERO;

            for (String categoria : categorias) {
                pedidos.stream().filter(p -> p.getCategoria().equals(categorias))
                        .forEach(p-> {
                            pedidosDestaCategoria.add(p);
                            montante.add(p.getValorTotal());
                        });
                quantidade = pedidos.stream().filter(p -> p.getCategoria().equals(categoria))
                        .map(p-> p.getQuantidade()).reduce(quantidade, Integer::sum);

                mapPedidosPorCategoria.put(categoria, pedidosDestaCategoria);
            }
        }
        return mapPedidosPorCategoria;
    }

     public void exibirEstatisticasCategorias(List<Categoria> categorias){
         if(categorias == null) return;
         categorias.stream().forEach(c -> System.out.println(c.toString()));
     }


}
