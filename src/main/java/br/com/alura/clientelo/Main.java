package br.com.alura.clientelo;

import br.com.alura.clientelo.estatisticas.CategoriaEstatistica;
import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.processor.ProcessadorDeCsv;
import br.com.alura.clientelo.processor.ProcessadorDeJson;
import br.com.alura.clientelo.service.CategoriasService;
import br.com.alura.clientelo.service.PedidosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.*;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws IOException, URISyntaxException {
        ProcessadorDeCsv processadorDeCsv = new ProcessadorDeCsv();
        ProcessadorDeJson processadorDeJson= new ProcessadorDeJson();
        //List<Pedido> listPedidosFromJson = processadorDeJson.processaArquivo("pedidos.json");
        List<Pedido> pedidos = processadorDeCsv.processaArquivo("pedidos.csv");
        CategoriasService categoriasService = new CategoriasService();
        PedidosService pedidosService = new PedidosService(pedidos);
        pedidosService = pedidosService.getEstatisticasGerais();

        logger.info("##### RELATÓRIO DE VALORES TOTAIS #####");
        logger.info("TOTAL DE PEDIDOS REALIZADOS: {}", pedidosService.getTotalDePedidosRealizados());
        logger.info("TOTAL DE PRODUTOS VENDIDOS: {}", pedidosService.getTotalDeProdutosVendidos());
        logger.info("TOTAL DE CATEGORIAS: {}", pedidosService.getTotalDeCategorias());
        logger.info("MONTANTE DE VENDAS: {}", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(pedidosService.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));
        logger.info("PEDIDO MAIS BARATO: {} ({})", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(pedidosService.getPedidoMaisBarato().getPreco().multiply(new BigDecimal(pedidosService.getPedidoMaisBarato().getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidosService.getPedidoMaisBarato().getProduto().getNome());
        logger.info("PEDIDO MAIS CARO: {} ({})\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(pedidosService.getPedidoMaisCaro().getPreco().multiply(new BigDecimal(pedidosService.getPedidoMaisCaro().getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidosService.getPedidoMaisCaro().getProduto().getNome());
        logger.info("### FIM DO RELATÓRIO ###");

        //Gerar relatórios Semana 1
        logger.info("##### RELATÓRIO DE PRODUTOS MAIS VENDIDOS #####");
        List<Produto> produtoMaisVendidos = pedidosService.produtoMaisVendidos();
        logger.info("-------------------");

        logger.info("##### RELATÓRIO DE VENDAS POR CATEGORIA #####");
        List<CategoriaEstatistica> detalhesCategoria = categoriasService.vendasPorCategoria(pedidos);
        logger.info("-------------------");

        logger.info("##### RELATÓRIO DE PRODUTO MAIS CAROS POR CATEGORIA #####");
        List<Produto> produtosMaisCaros = categoriasService.produtoMaisCaroPorCategoria(detalhesCategoria, pedidos);
        logger.info("-------------------");
    }
}
