package br.com.alura.clientelo.vo.cliente;

import java.math.BigDecimal;
import java.util.Objects;

public class RelatorioClienteFiel {

    private String nome;
    private Long qtdPedidos;
    private BigDecimal montante;

    public RelatorioClienteFiel(String nome, Long qtdPedidos, BigDecimal montante) {
        this.nome = nome;
        this.qtdPedidos = qtdPedidos;
        this.montante = montante;
    }

    public RelatorioClienteFiel() {
    }

    public String getNome() {
        return nome;
    }

    public Long getQtdPedidos() {
        return qtdPedidos;
    }

    public BigDecimal getMontante() {
        return montante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatorioClienteFiel that = (RelatorioClienteFiel) o;
        return  (Objects.equals(nome, that.getNome())
                && Objects.equals(qtdPedidos, that.getQtdPedidos())
                && montante.compareTo(that.getMontante()) == 0);
    }

    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }
}
