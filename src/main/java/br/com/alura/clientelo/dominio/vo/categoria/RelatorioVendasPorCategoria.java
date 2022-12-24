package br.com.alura.clientelo.dominio.vo.categoria;

import java.math.BigDecimal;
import java.util.Objects;

public class RelatorioVendasPorCategoria {

    private String nomeCategoria;
    private Long qtdDeProdutosVendidos;
    private BigDecimal montanteDeVendas;

    public RelatorioVendasPorCategoria(String nomeCategoria, Long qtdDeProdutosVendidos, BigDecimal montanteDeVendas) {
        this.nomeCategoria = nomeCategoria;
        this.qtdDeProdutosVendidos = qtdDeProdutosVendidos;
        this.montanteDeVendas = montanteDeVendas;
    }

    public RelatorioVendasPorCategoria(){}

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Long getQtdDeProdutosVendidos() {
        return qtdDeProdutosVendidos;
    }

    public BigDecimal getMontanteDeVendas() {
        return montanteDeVendas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatorioVendasPorCategoria that = (RelatorioVendasPorCategoria) o;
        return  (Objects.equals(nomeCategoria, that.getNomeCategoria())
                && Objects.equals(qtdDeProdutosVendidos, that.getQtdDeProdutosVendidos())
                && montanteDeVendas.compareTo(that.getMontanteDeVendas()) == 0);
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }
}
