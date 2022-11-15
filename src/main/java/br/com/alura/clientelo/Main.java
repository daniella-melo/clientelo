package br.com.alura.clientelo;

import br.com.alura.clientelo.dao.PedidoDao;
import br.com.alura.clientelo.estatisticas.CategoriaEstatistica;
import br.com.alura.clientelo.estatisticas.PedidoEstatistica;
import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
//import br.com.alura.clientelo.processor.ProcessadorDeCsv;
//import br.com.alura.clientelo.processor.ProcessadorDeJson;
//import br.com.alura.clientelo.service.CategoriasService;
//import br.com.alura.clientelo.service.PedidosService;
import br.com.alura.clientelo.util.JPAUtil;
import br.com.alura.clientelo.vo.RelatorioVendasPorCategoria;
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
        JPAUtil jpaUtil = new JPAUtil();
        PedidoDao pedidoDao = new PedidoDao(JPAUtil.getEntityManager());

        List<RelatorioVendasPorCategoria> relatorio1 = pedidoDao.vendasPorCategoria();
    }
}
