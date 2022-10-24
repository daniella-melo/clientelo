package br.com.alura.clientelo;

import br.com.alura.clientelo.model.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

public class PedidosEstatisticas {
    private CategoriasEstatisticas categoriasEstatisticas = new CategoriasEstatisticas();
    private List<Pedido> pedidos;
    private int totalDeProdutosVendidos;
    private int totalDePedidosRealizados;
    private BigDecimal montanteDeVendas;
    private Pedido pedidoMaisBarato;
    private Pedido pedidoMaisCaro;

    private int totalDeCategorias;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public PedidosEstatisticas(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        this.totalDeProdutosVendidos = 0;
        this.totalDePedidosRealizados = 0;
        this.montanteDeVendas = BigDecimal.ZERO;
        this.pedidoMaisBarato = null;
        this.pedidoMaisCaro = null;
        this.totalDeCategorias = 0;
    }

    public List<Pedido> maisVendidos(){
        pedidos.sort(Comparator.comparing(Pedido::getQuantidade).reversed());
        logMaisVendidos(pedidos);
        return pedidos;
    }

    public PedidosEstatisticas getEstatisticasGerais(){
        String[] categoriasProcessadas = new String[10];

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

    private void logMaisVendidos(List<Pedido> pedidos){
        for (Pedido pedido : this.pedidos) {
            logger.info("PRODUTO: {}",pedido.getProduto());
            logger.info("QUANTIDADE: {}", pedido.getQuantidade());
        }
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
    public int getTotalDeCategorias() {return totalDeCategorias;}
}
