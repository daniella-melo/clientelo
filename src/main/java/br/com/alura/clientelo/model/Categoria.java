package br.com.alura.clientelo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Categoria  {

    private String nome;
    private BigDecimal montanteDeVendas;
    private int qtdDeVendas;
    private List<Pedido> pedidos;

    private Pedido pedidoComProdutoMaisCaro;

    public Categoria(String nome, BigDecimal montanteDeVendas, int qtdDeVendas) {
        if(nome==null){
            throw new NullPointerException();
        }
        this.nome = nome;
        this.montanteDeVendas = montanteDeVendas;
        this.qtdDeVendas = qtdDeVendas;
        this.pedidos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
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

    @Override
    public String toString(){
        return "CATEGORIA: " + nome + "\n" +
                "QUANTIDADE: " + qtdDeVendas + "\n" + "MONTANTE: " + montanteDeVendas + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria that = (Categoria) o;
        return Objects.equals(nome, that.getNome()) &&
                Objects.equals(qtdDeVendas, that.getQtdDeVendas())
                && Objects.equals(montanteDeVendas, that.getMontanteDeVendas());
    }

    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }
}

