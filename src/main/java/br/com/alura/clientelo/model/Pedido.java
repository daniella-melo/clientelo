package br.com.alura.clientelo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Pedido{

    private String categoria;
    private String produto;
    private String cliente;

    private BigDecimal preco;
    private int quantidade;

    private LocalDate data;

    private BigDecimal valorTotal;

    public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
        if(categoria == null || produto == null || cliente == null
                || preco == null || quantidade == 0 || data == null){
            throw new NullPointerException();
        }
        this.categoria = categoria;
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.valorTotal = this.preco.multiply(new BigDecimal(this.quantidade));
        this.data = data;
    }
    public boolean isMaisBaratoQue(Pedido outroPedido) {
        if(this.valorTotal.compareTo(outroPedido.getValorTotal())< 0) return true;
        return false;
    }

    public boolean isMaisCaroQue(Pedido outroPedido) {
        if(this.valorTotal.compareTo(outroPedido.getValorTotal()) > 0) return true;
        return false;
    }

    public BigDecimal getValorTotal() {
        return this.valorTotal;
    }
    public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", produto='" + produto + '\'' +
                ", cliente='" + cliente + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido that = (Pedido) o;
        return Objects.equals(categoria, that.getCategoria()) && Objects.equals(quantidade, that.getQuantidade()) &&
                Objects.equals(preco, that.getPreco()) && Objects.equals(produto, that.getProduto()) &&
                Objects.equals(cliente, that.getCliente()) && Objects.equals(data, that.getData()) &&
                Objects.equals(valorTotal, that.getValorTotal());
    }
    @Override
    public int hashCode() {
        return this.produto.hashCode();
    }

}
