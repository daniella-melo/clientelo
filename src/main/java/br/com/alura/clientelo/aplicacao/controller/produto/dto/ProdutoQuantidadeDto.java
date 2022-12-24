package br.com.alura.clientelo.aplicacao.controller.produto.dto;

import br.com.alura.clientelo.dominio.model.pedido.ItemDePedido;
import br.com.alura.clientelo.dominio.model.produto.Produto;
import br.com.alura.clientelo.dominio.model.pedido.TipoDescontoEnum;
import br.com.alura.clientelo.aplicacao.service.produto.ProdutoService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

public class ProdutoQuantidadeDto {

    @NotNull
    private Long idProduto;

    @Min(1)
    private int quantidade;

    public ProdutoQuantidadeDto(Long idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean valido(ProdutoService service){
        Optional<Produto> recoverd = service.getById(idProduto);
        return (recoverd.isPresent() && recoverd.get().getQntEmEstoque()>0);
    }

    public ItemDePedido convertToItemPedido(ProdutoService service){
        Optional<Produto> recoverd = service.getById(idProduto);
        BigDecimal desconto = BigDecimal.ZERO;
        return new ItemDePedido(quantidade, recoverd.get(), desconto, TipoDescontoEnum.QUANTIDADE);
    }
}
