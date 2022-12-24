package br.com.alura.clientelo.aplicacao.controller.pedido.form;

import br.com.alura.clientelo.aplicacao.controller.produto.dto.ProdutoQuantidadeDto;
import br.com.alura.clientelo.dominio.model.cliente.Cliente;
import br.com.alura.clientelo.dominio.model.pedido.ItemDePedido;
import br.com.alura.clientelo.dominio.model.pedido.Pedido;
import br.com.alura.clientelo.dominio.model.pedido.TipoDescontoEnum;
import br.com.alura.clientelo.aplicacao.service.cliente.ClienteService;
import br.com.alura.clientelo.aplicacao.service.pedido.PedidoService;
import br.com.alura.clientelo.aplicacao.service.produto.ProdutoService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PedidoForm {

    @NotNull
    private Long idCliente;
    private List<ProdutoQuantidadeDto> listProdutoQntd;

    public PedidoForm(Long idCliente, List<ProdutoQuantidadeDto> listProdutoQntd) {
        this.idCliente = idCliente;
        this.listProdutoQntd = listProdutoQntd;
    }

    public PedidoForm(Long idCliente) {
        this.idCliente = idCliente;
        this.listProdutoQntd = new ArrayList<>();
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public List<ProdutoQuantidadeDto> getListProdutoQntd() {
        return listProdutoQntd;
    }

    public boolean valido(ClienteService clienteService, ProdutoService produtoService) {
        for (ProdutoQuantidadeDto pq: listProdutoQntd) {
            if(!pq.valido(produtoService)) return false;
        }
        return clienteService.getById(idCliente) != null;
    }

    public Pedido converter(ClienteService clienteService, ProdutoService produtoService, PedidoService pedidoService) {
        Cliente cliente = clienteService.getById(idCliente).get();
        Pedido novo = new Pedido(cliente, TipoDescontoEnum.NENHUM);
        pedidoService.aplicarDescontos(novo);

        for (ProdutoQuantidadeDto pq: listProdutoQntd) {
            ItemDePedido item = pq.convertToItemPedido(produtoService);
            novo.adicionarItem(item);
        }
        return novo;
    }
}
