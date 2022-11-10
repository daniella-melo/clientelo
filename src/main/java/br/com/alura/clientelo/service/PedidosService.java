package br.com.alura.clientelo.service;

import br.com.alura.clientelo.Main;
import br.com.alura.clientelo.estatisticas.PedidoEstatistica;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PedidosService {
    private CategoriasService categoriasService = new CategoriasService();
    private Map<Pedido, PedidoEstatistica> mapPedidos;
    private int totalDeProdutosVendidos;
    private int totalDePedidosRealizados;
    private BigDecimal montanteDeVendas;
    private PedidoEstatistica pedidoMaisBarato;
    private PedidoEstatistica pedidoMaisCaro;

    private int totalDeCategorias;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public PedidosService(Map<Pedido, PedidoEstatistica> mapPedidos) {
        this.mapPedidos = mapPedidos;
        this.totalDeProdutosVendidos = 0;
        this.totalDePedidosRealizados = 0;
        this.montanteDeVendas = BigDecimal.ZERO;
        this.pedidoMaisBarato = null;
        this.pedidoMaisCaro = null;
        this.totalDeCategorias = 0;
    }

    private Set<Produto> getProdutoFromPedidos(){
        Set<Produto> produtos = new HashSet<>();
        List<PedidoEstatistica> pedidoEstatisticas =  this.mapPedidos.values().stream().toList();
        for (PedidoEstatistica pe: pedidoEstatisticas) {
            if(pe == null) break;
            produtos.add(pe.getProduto());
        }
        return produtos;
    }
    public List<Produto> produtoMaisVendidos(){
        if(this.mapPedidos == null) return null;
        List<Produto> listProdutos = new ArrayList<>();
        Set<Produto> produtos = getProdutoFromPedidos();

        listProdutos = montarProdutos(produtos);

        listProdutos.sort(Comparator.comparing(Produto::getQtdDeVendas).reversed());
        logMaisVendidos(listProdutos);
        return listProdutos;
    }

    private List<Produto> montarProdutos(Set<Produto> setProdutos){
        List<Produto> listProdutos = new ArrayList<>();
        List<PedidoEstatistica> pedidoEstatisticas =  this.mapPedidos.values().stream().toList();
        for (Produto produto: setProdutos) {
            int quantidade = 0;
            String categoria = null;
            BigDecimal precoUnitario = BigDecimal.ZERO;

            quantidade = pedidoEstatisticas.stream().filter(p -> p.getProduto().equals(produto)).map(p->p.getQuantidade()).reduce(quantidade, Integer::sum);
            categoria = pedidoEstatisticas.stream().filter(p -> p.getProduto().equals(produto)).map(p -> p.getPedido().getCategoria()).findFirst().get();
            precoUnitario = pedidoEstatisticas.stream().filter(p -> p.getProduto().equals(produto)).map(p -> p.getPreco()
                    .divide(new BigDecimal(p.getQuantidade()), 2, RoundingMode.HALF_UP)).findFirst().orElse(null);

            produto.setCategoria(categoria);
            produto.setPrecoUnitario(precoUnitario);
            produto.setQtdDeVendas(quantidade);

            listProdutos.add(produto);
        }
        return listProdutos;
    }
    public PedidosService getEstatisticasGerais(){
        String[] categoriasProcessadas = new String[10];
        List<PedidoEstatistica> pedidoEstatisticas =  this.mapPedidos.values().stream().toList();

        for (int i = 0; i < pedidoEstatisticas.size(); i++) {
            PedidoEstatistica pedidoAtual = pedidoEstatisticas.get(i);

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
                if (pedidoAtual.getPedido().getCategoria().equalsIgnoreCase(categoriasProcessadas[j])) {
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
                            categoriasProcessadas[k] = pedidoAtual.getPedido().getCategoria();
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
    public PedidoEstatistica getPedidoMaisBarato() {
        return pedidoMaisBarato;
    }
    public PedidoEstatistica getPedidoMaisCaro() {
        return pedidoMaisCaro;
    }
    public int getTotalDeCategorias() {return totalDeCategorias;}
}
