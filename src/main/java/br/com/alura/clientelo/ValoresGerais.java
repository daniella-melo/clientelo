package br.com.alura.clientelo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ValoresGerais {
    private int totalDeProdutosVendidos;
    private int totalDePedidosRealizados;
    private BigDecimal montanteDeVendas;
    private Pedido pedidoMaisBarato;

    private Pedido pedidoMaisCaro;
    private int totalDeCategorias;
    public ValoresGerais getAll(List<Pedido> pedidos) {
        String[] categoriasProcessadas = new String[10];
        totalDePedidosRealizados = 0;
        totalDeProdutosVendidos = 0;
        montanteDeVendas = BigDecimal.ZERO;
        pedidoMaisBarato = null;
        pedidoMaisCaro = null;
        totalDeCategorias = 0;

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedidoAtual = pedidos.get(i);

            if (pedidoAtual == null) {
                break;
            }

            if (this.pedidoMaisBarato == null || pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())).compareTo(pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade()))) < 0) {
                this.pedidoMaisBarato = pedidoAtual;
            }

            if (this.pedidoMaisCaro == null || pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())).compareTo(pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade()))) > 0) {
                this.pedidoMaisCaro = pedidoAtual;
            }

            this.montanteDeVendas =  this.montanteDeVendas.add(pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())));
            this.totalDeProdutosVendidos += pedidoAtual.getQuantidade();
            this.totalDePedidosRealizados++;

            boolean jahProcessouCategoria = false;
            for (int j = 0; j < categoriasProcessadas.length; j++) {
                if (pedidoAtual.getCategoria().equalsIgnoreCase(categoriasProcessadas[j])) {
                    jahProcessouCategoria = true;
                }
            }

            if (!jahProcessouCategoria) {
                totalDeCategorias++;

                if (categoriasProcessadas[categoriasProcessadas.length - 1] != null) {
                    categoriasProcessadas = Arrays.copyOf(categoriasProcessadas, categoriasProcessadas.length * 2);
                } else {
                    for (int k = 0; k < categoriasProcessadas.length; k++) {
                        if (categoriasProcessadas[k] == null) {
                            categoriasProcessadas[k] = pedidoAtual.getCategoria();
                            break;
                        }
                    }
                }
            }
        }
        return this;
    }


    public int getTotalDeProdutosVendidos() {
        return totalDeProdutosVendidos;
    }

    public int getTotalDePedidosRealizados() {
        return totalDePedidosRealizados;
    }

    public BigDecimal getMontanteDeVendas() {
        return montanteDeVendas;
    }

    public Pedido getPedidoMaisBarato() {
        return pedidoMaisBarato;
    }

    public Pedido getPedidoMaisCaro() {
        return pedidoMaisCaro;
    }

    public int getTotalDeCategorias() {
        return totalDeCategorias;
    }

}
