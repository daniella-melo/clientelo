package br.com.alura.clientelo.util;

import br.com.alura.clientelo.model.Pedido;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class RelatorioSintetico {
    private int totalDeProdutosVendidos= 0;
    private int totalDePedidosRealizados= 0;
    private BigDecimal montanteDeVendas = BigDecimal.ZERO;
    private Pedido pedidoMaisBarato = null;

    private Pedido pedidoMaisCaro = null;
    private int totalDeCategorias = 0;
    public RelatorioSintetico getAll(List<Pedido> pedidos) {
        String[] categoriasProcessadas = new String[10];

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedidoAtual = pedidos.get(i);

            if (pedidoAtual == null) {
                break;
            }

            if (isPedidoMaisBarato(pedidoAtual)) {
                this.pedidoMaisBarato = pedidoAtual;
            }

            if (isPedidoMaisCaro(pedidoAtual)) {
                this.pedidoMaisCaro = pedidoAtual;
            }

            this.montanteDeVendas = addToMontanteDeVendas(pedidoAtual);
            this.totalDeProdutosVendidos += pedidoAtual.getQuantidade();
            this.totalDePedidosRealizados++;

            boolean jahProcessouCategoria = false;
            for (int j = 0; j < categoriasProcessadas.length; j++) {
                if (categoriaJaProcessada(pedidoAtual, categoriasProcessadas[j])) {
                    jahProcessouCategoria = true;
                }
            }
            if (!jahProcessouCategoria) {
                categoriasProcessadas = addToCategoriasProcessadas(categoriasProcessadas, pedidoAtual);
            }
        }
        return this;
    }

    private String[] addToCategoriasProcessadas(String[] categoriasProcessadas, Pedido pedidoAtual) {
        this.totalDeCategorias++;
        String[] result = categoriasProcessadas;

        if (categoriasProcessadas[categoriasProcessadas.length - 1] != null) {
            result = getCopiaExpandidaArraydeCategoriasJaProcessadas(categoriasProcessadas);
        } else {
            for (int k = 0; k < categoriasProcessadas.length; k++) {
                if (categoriasProcessadas[k] == null) {
                    result[k] = pedidoAtual.getCategoria();
                    break;
                }
            }
        }
        return result;
    }

    private static String[] getCopiaExpandidaArraydeCategoriasJaProcessadas(String[] categoriasProcessadas) {
        return Arrays.copyOf(categoriasProcessadas, categoriasProcessadas.length * 2);
    }

    private static boolean categoriaJaProcessada(Pedido pedidoAtual, String categoriasProcessadas) {
        return pedidoAtual.getCategoria().equalsIgnoreCase(categoriasProcessadas);
    }

    private BigDecimal addToMontanteDeVendas(Pedido pedidoAtual) {
        return this.montanteDeVendas.add(pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())));
    }

    private boolean isPedidoMaisCaro(Pedido pedidoAtual) {
        return this.pedidoMaisCaro == null || pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())).compareTo(pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade()))) > 0;
    }

    private boolean isPedidoMaisBarato(Pedido pedidoAtual) {
        return this.pedidoMaisBarato == null || pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())).compareTo(pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade()))) < 0;
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
