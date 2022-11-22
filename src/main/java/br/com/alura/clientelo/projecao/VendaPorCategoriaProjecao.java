package br.com.alura.clientelo.projecao;

import java.math.BigDecimal;

public interface VendaPorCategoriaProjecao {
    String getNomeCategoria();

    Long getQtdDeProdutosVendidos();

    BigDecimal getMontanteDeVendas();
}
