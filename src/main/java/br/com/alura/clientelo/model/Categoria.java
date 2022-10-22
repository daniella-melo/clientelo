package br.com.alura.clientelo.model;

import java.math.BigDecimal;
import java.util.List;

public class Categoria {

    private String categoria;
    private BigDecimal montanteDeVendas;
    private int qtdDeVendas;
    private List<Pedido> pedidos;

    private Pedido pedidoComProdutoMaisCaro;

    public Categoria(String categoria, BigDecimal montanteDeVendas, int qtdDeVendas) {
        this.categoria = categoria;
        this.montanteDeVendas = montanteDeVendas;
        this.qtdDeVendas = qtdDeVendas;
    }

    public void addPedido(Pedido pedido){
        if(pedido==null) return;
        pedidos.add(pedido);
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getMontanteDeVendas() {
        return montanteDeVendas;
    }

    public int getQtdDeVendas() {
        return qtdDeVendas;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    @Override
    public String toString(){
        return "CATEGORIA: " + categoria + "\n" +
                "QUANTIDADE: " + qtdDeVendas + "\n" + "MONTANTE: " + montanteDeVendas + "\n";
    }
}
