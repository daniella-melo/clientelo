package br.com.alura.clientelo.dominio.projecao.categoria;

import java.math.BigDecimal;

public interface VendaPorCategoriaProjecao {
    String getNomeCategoria();

    Long getQtdDeProdutosVendidos();

    BigDecimal getMontanteDeVendas();
}
