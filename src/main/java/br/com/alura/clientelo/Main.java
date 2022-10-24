package br.com.alura.clientelo;

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
        ValoresGerais valoresGerais = new ValoresGerais();
        valoresGerais = valoresGerais.getAll(pedidos);

        logger.info("##### RELATÓRIO DE VALORES TOTAIS #####");
        logger.info("TOTAL DE PEDIDOS REALIZADOS: {}", valoresGerais.getTotalDePedidosRealizados());
        logger.info("TOTAL DE PRODUTOS VENDIDOS: {}", valoresGerais.getTotalDeProdutosVendidos());
        logger.info("TOTAL DE CATEGORIAS: {}", valoresGerais.getTotalDeCategorias());
        logger.info("MONTANTE DE VENDAS: {}", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valoresGerais.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));
        logger.info("PEDIDO MAIS BARATO: {} ({})", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valoresGerais.getPedidoMaisBarato().getPreco().multiply(new BigDecimal(valoresGerais.getPedidoMaisBarato().getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), valoresGerais.getPedidoMaisBarato().getProduto());
        logger.info("PEDIDO MAIS CARO: {} ({})\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valoresGerais.getPedidoMaisCaro().getPreco().multiply(new BigDecimal(valoresGerais.getPedidoMaisCaro().getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), valoresGerais.getPedidoMaisCaro().getProduto());
        logger.info("### FIM DO RELATÓRIO ###");

        Relatorios relatorios = new Relatorios();
        //Gerar relatórios Semana 1
        logger.info("##### RELATÓRIO DE PRODUTOS MAIS VENDIDOS #####");
        relatorios.produtoMaisVendidos(pedidos);
        logger.info("-------------------");

        logger.info("##### RELATÓRIO DE VENDAS POR CATEGORIA #####");
        HashMap<String, ArrayList<Pedido>> mapPedidosPorCategoria = relatorios.vendasPorCategoria(pedidos, valoresGerais.getTotalDeCategorias());
        logger.info("-------------------");

        logger.info("##### RELATÓRIO DE PRODUTO MAIS CAROS POR CATEGORIA #####");
        relatorios.produtoMaisCaroPorCategoria(mapPedidosPorCategoria);
        logger.info("-------------------");
    }
}
