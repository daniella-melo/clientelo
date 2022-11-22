package br.com.alura.clientelo.projecao;

import java.math.BigDecimal;

public interface ClienteFielProjecao {
    String getNome();

    Long getQtdPedidos();

    BigDecimal getMontante();
}
