package br.com.alura.clientelo;

import br.com.alura.clientelo.model.Pedido;
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
        List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
        PedidosEstatisticas pedidosEstatisticas = new PedidosEstatisticas(pedidos);
        pedidosEstatisticas = pedidosEstatisticas.getEstatisticasGerais();

        logger.info("##### RELATÓRIO DE VALORES TOTAIS #####");
        logger.info("TOTAL DE PEDIDOS REALIZADOS: {}", pedidosEstatisticas.getTotalDePedidosRealizados());
        logger.info("TOTAL DE PRODUTOS VENDIDOS: {}", pedidosEstatisticas.getTotalDeProdutosVendidos());
        logger.info("TOTAL DE CATEGORIAS: {}", pedidosEstatisticas.getTotalDeCategorias());
        logger.info("MONTANTE DE VENDAS: {}", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(pedidosEstatisticas.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));
        logger.info("PEDIDO MAIS BARATO: {} ({})", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(pedidosEstatisticas.getPedidoMaisBarato().getPreco().multiply(new BigDecimal(pedidosEstatisticas.getPedidoMaisBarato().getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidosEstatisticas.getPedidoMaisBarato().getProduto());
        logger.info("PEDIDO MAIS CARO: {} ({})\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(pedidosEstatisticas.getPedidoMaisCaro().getPreco().multiply(new BigDecimal(pedidosEstatisticas.getPedidoMaisCaro().getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidosEstatisticas.getPedidoMaisCaro().getProduto());
        logger.info("### FIM DO RELATÓRIO ###");

        //Gerar relatórios Semana 1
        logger.info("##### RELATÓRIO DE PRODUTOS MAIS VENDIDOS #####");
        pedidosEstatisticas.maisVendidos();
        logger.info("-------------------");

        logger.info("##### RELATÓRIO DE VENDAS POR CATEGORIA #####");
       // HashMap<String, ArrayList<Pedido>> mapPedidosPorCategoria = relatorios.vendasPorCategoria(pedidos);
        logger.info("-------------------");

        logger.info("##### RELATÓRIO DE PRODUTO MAIS CAROS POR CATEGORIA #####");
        //relatorios.produtoMaisCaroPorCategoria(mapPedidosPorCategoria);
        logger.info("-------------------");
    }
}
