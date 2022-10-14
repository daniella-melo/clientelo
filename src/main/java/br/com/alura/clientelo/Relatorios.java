package br.com.alura.clientelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Relatorios {

    public void maisVendidos(Pedido[] pedidos){
        List<Pedido> listPedidos = this.convertToArrayList(pedidos);
        listPedidos.sort(Comparator.comparing(Pedido::getQuantidade).reversed());

        for (Pedido pedido : listPedidos) {
            System.out.println("PRODUTO: " + pedido.getProduto());
            System.out.println("QUANTIDADE: " + pedido.getQuantidade());
        }
    }

    public List<Pedido> convertToArrayList(Pedido[] pedidos){
        List<Pedido> listPedidos = new ArrayList<Pedido>();
        for (int i = 0; i < pedidos.length; i++) {
            listPedidos.add(pedidos[i]);
        }
        return listPedidos;
    }
}
