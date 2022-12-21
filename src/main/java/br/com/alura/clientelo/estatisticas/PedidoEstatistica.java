package br.com.alura.clientelo.estatisticas;

import br.com.alura.clientelo.model.pedido.Pedido;
import br.com.alura.clientelo.model.produto.Produto;

import java.math.BigDecimal;

public class PedidoEstatistica {
    private Pedido pedido;
    private BigDecimal preco;
    private Produto produto;
    private int quantidade;
    private BigDecimal valorTotal;

    public PedidoEstatistica(Pedido pedido, BigDecimal preco, Produto produto, int quantidade) {
        this.pedido = pedido;
        this.preco = preco;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = this.preco.multiply(new BigDecimal(this.quantidade));
    }

    public boolean isMaisBaratoQue(PedidoEstatistica outroPedido) {
        if(this.valorTotal.compareTo(outroPedido.getValorTotal())< 0) return true;
        return false;
    }

    public boolean isMaisCaroQue(PedidoEstatistica outroPedido) {
        if(this.valorTotal.compareTo(outroPedido.getValorTotal()) > 0) return true;
        return false;
    }


    public BigDecimal getPreco() {
        return preco;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Pedido getPedido() {
        return pedido;
    }
}
