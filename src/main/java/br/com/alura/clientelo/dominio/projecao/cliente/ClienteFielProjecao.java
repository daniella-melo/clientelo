package br.com.alura.clientelo.dominio.projecao.cliente;

import java.math.BigDecimal;

public interface ClienteFielProjecao {
    String getNome();

    Long getQtdPedidos();

    BigDecimal getMontante();
}
