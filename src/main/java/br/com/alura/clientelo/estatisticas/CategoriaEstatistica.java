package br.com.alura.clientelo.estatisticas;

import br.com.alura.clientelo.model.categoria.Categoria;
import br.com.alura.clientelo.model.pedido.Pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CategoriaEstatistica {
    private Categoria categoria;
    private BigDecimal montanteDeVendas;

    private int qtdDeVendas;

    private List<Pedido> pedidos;

    public CategoriaEstatistica(Categoria categoria) {
        this.categoria = categoria;
        this.pedidos = new ArrayList<>();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public BigDecimal getMontanteDeVendas() {
        return montanteDeVendas;
    }

    public int getQtdDeVendas() {
        return qtdDeVendas;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setMontanteDeVendas(BigDecimal montanteDeVendas) {
        this.montanteDeVendas = montanteDeVendas;
    }

    public void setQtdDeVendas(int qtdDeVendas) {
        this.qtdDeVendas = qtdDeVendas;
    }
}
