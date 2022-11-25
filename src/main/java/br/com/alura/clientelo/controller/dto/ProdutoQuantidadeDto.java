package br.com.alura.clientelo.controller.dto;

import br.com.alura.clientelo.model.ItemDePedido;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.model.TipoDescontoEnum;
import br.com.alura.clientelo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

public class ProdutoQuantidadeDto {

    @Autowired
    private ProdutoService service;

    private Long idProduto;
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

    public boolean valido(){
        Optional<Produto> recoverd = service.getById(idProduto);
        return (recoverd.isPresent() && recoverd.get().getQntEmEstoque()>0);
    }

    public ItemDePedido convertToItemPedido(){
        Optional<Produto> recoverd = service.getById(idProduto);
        BigDecimal desconto = BigDecimal.ZERO;
        if(quantidade>10){
            desconto = new BigDecimal(0.1);
            return new ItemDePedido(quantidade, recoverd.get(), desconto, TipoDescontoEnum.QUANTIDADE);
        }
        return new ItemDePedido(quantidade, recoverd.get(), desconto, TipoDescontoEnum.NENHUM);
    }
}
