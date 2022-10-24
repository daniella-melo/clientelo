package br.com.alura.clientelo;

import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
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

    public List<Produto> produtoMaisVendidos(){
        if(this.pedidos == null) return null;
        List<Produto> listProdutos = new ArrayList<>();
        Set<String> produtos = new HashSet<>();
        for (Pedido pedido: this.pedidos) {
            if(pedido == null) break;
            produtos.add(pedido.getProduto());
        }

        for (String produto: produtos) {
            int quantidade = 0;
            String categoria = null;
            BigDecimal montante = BigDecimal.ZERO;

            quantidade = this.pedidos.stream().filter(p -> p.getProduto().equals(produto)).map(p->p.getQuantidade()).reduce(quantidade, Integer::sum);
            categoria = this.pedidos.stream().filter(p -> p.getProduto().equals(produto)).map(p -> p.getCategoria()).findFirst().get();

            Produto newProduto = new Produto(produto, categoria, quantidade);
            listProdutos.add(newProduto);
        }

        listProdutos.sort(Comparator.comparing(Produto::getQtdDeVendas).reversed());
        for (Produto produto : listProdutos) {
            logger.info("PRODUTO: {}",produto.getNome());
            logger.info("QUANTIDADE: {}", produto.getQtdDeVendas());
        }
        return listProdutos;
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
