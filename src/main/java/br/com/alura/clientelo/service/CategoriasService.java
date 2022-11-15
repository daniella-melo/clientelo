package br.com.alura.clientelo.service;

import br.com.alura.clientelo.Main;
import br.com.alura.clientelo.estatisticas.CategoriaEstatistica;
import br.com.alura.clientelo.estatisticas.PedidoEstatistica;
import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class CategoriasService {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

     private void logDetalhesCategorias(List<CategoriaEstatistica> categorias){
         if(categorias == null) return;
         for (CategoriaEstatistica categoria:categorias) {
             logger.info("CATEGORIA: {}",categoria.getCategoria().getNome());
             logger.info("QUANTIDADE: {}",categoria.getQtdDeVendas());
             logger.info("MONTANTE: {}",categoria.getMontanteDeVendas());
         }
     }

    private void logProdutoMaisCaroPorCategoria(String categoria, String produtoMaisCaro, BigDecimal precoProdutoMaisCaro){
         logger.info("CATEGORIA: {}",categoria);
         logger.info("PRODUTO: {}",produtoMaisCaro);
         logger.info("PRECO (1 unidade): {}",precoProdutoMaisCaro);
     }

}
