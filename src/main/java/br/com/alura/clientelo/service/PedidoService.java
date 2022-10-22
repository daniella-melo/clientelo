package br.com.alura.clientelo.service;

import br.com.alura.clientelo.Main;
import br.com.alura.clientelo.model.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

public class PedidoService {
    private CategoriaService categoriaService = new CategoriaService();
    private List<Pedido> pedidos;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public PedidoService(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void maisVendidos(){
        pedidos.sort(Comparator.comparing(Pedido::getQuantidade).reversed());

        for (Pedido pedido : this.pedidos) {
            logger.info("PRODUTO: {}",pedido.getProduto());
            logger.info("QUANTIDADE: {}", pedido.getQuantidade());
        }
    }

}
