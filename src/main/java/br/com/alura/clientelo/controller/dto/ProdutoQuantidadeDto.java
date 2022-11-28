package br.com.alura.clientelo.controller.dto;

import br.com.alura.clientelo.model.ItemDePedido;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.model.TipoDescontoEnum;
import br.com.alura.clientelo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;

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
