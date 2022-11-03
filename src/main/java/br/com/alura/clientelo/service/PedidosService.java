package br.com.alura.clientelo.service;

import br.com.alura.clientelo.Main;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PedidosService {
    private CategoriasService categoriasService = new CategoriasService();
    private List<Pedido> pedidos;
    private int totalDeProdutosVendidos;
    private int totalDePedidosRealizados;
    private BigDecimal montanteDeVendas;
    private Pedido pedidoMaisBarato;
    private Pedido pedidoMaisCaro;

    private int totalDeCategorias;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public PedidosService(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        this.totalDeProdutosVendidos = 0;
        this.totalDePedidosRealizados = 0;
        this.montanteDeVendas = BigDecimal.ZERO;
        this.pedidoMaisBarato = null;
        this.pedidoMaisCaro = null;
        this.totalDeCategorias = 0;
    }

    private Set<String> getProdutoFromPedidos(){
        Set<String> produtos = new HashSet<>();
        for (Pedido pedido: this.pedidos) {
            if(pedido == null) break;
            produtos.add(pedido.getProduto());
        }
        return produtos;
    }
    public List<Produto> produtoMaisVendidos(){
        if(this.pedidos == null) return null;
        List<Produto> listProdutos = new ArrayList<>();
        Set<String> produtos = getProdutoFromPedidos();

        listProdutos = montarProdutos(produtos);

        listProdutos.sort(Comparator.comparing(Produto::getQtdDeVendas).reversed());
        logMaisVendidos(listProdutos);
        return listProdutos;
    }

    private List<Produto> montarProdutos(Set<String> setProdutos){
        List<Produto> listProdutos = new ArrayList<>();
        for (String produto: setProdutos) {
            int quantidade = 0;
            String categoria = null;
            BigDecimal precoUnitario = BigDecimal.ZERO;

            quantidade = this.pedidos.stream().filter(p -> p.getProduto().equals(produto)).map(p->p.getQuantidade()).reduce(quantidade, Integer::sum);
            categoria = this.pedidos.stream().filter(p -> p.getProduto().equals(produto)).map(p -> p.getCategoria()).findFirst().get();
            precoUnitario = pedidos.stream().filter(p -> p.getProduto().equals(produto)).map(p -> p.getPreco()
                    .divide(new BigDecimal(p.getQuantidade()), 2, RoundingMode.HALF_UP)).findFirst().orElse(null);

            Produto newProduto = new Produto(produto, categoria, precoUnitario);
            listProdutos.add(newProduto);
        }
        return listProdutos;
    }
    public PedidosService getEstatisticasGerais(){
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

    private void logMaisVendidos(List<Produto> produtos){
        for (Produto produto : produtos) {
            logger.info("PRODUTO: {}",produto.getNome());
            logger.info("QUANTIDADE: {}", produto.getQtdDeVendas());
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
