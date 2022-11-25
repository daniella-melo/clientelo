package br.com.alura.clientelo.controller.form;

import br.com.alura.clientelo.controller.dto.ProdutoQuantidadeDto;
import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.ItemDePedido;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.TipoDescontoEnum;
import br.com.alura.clientelo.service.ClienteService;
import br.com.alura.clientelo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PedidoForm {

    @Autowired
    private ClienteService clienteService;

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

    public boolean valido() {
        for (ProdutoQuantidadeDto pq: listProdutoQntd) {
            if(!pq.valido()) return false;
        }
        return clienteService.getById(idCliente) != null;
    }

    public Pedido converter() {
        Cliente cliente = clienteService.getById(idCliente).get();
        int qntPedidosDoCliente = clienteService.getTotalDePedidosDoCliente(cliente.getId());

        Pedido novo;
        BigDecimal desconto = BigDecimal.ZERO;
        if(qntPedidosDoCliente > 5){
            desconto = new BigDecimal(0.05);
            novo = new Pedido(cliente, desconto, TipoDescontoEnum.FIDELIDADE);
        }else{
            novo = new Pedido(cliente, desconto, TipoDescontoEnum.NENHUM);
        }

        for (ProdutoQuantidadeDto pq: listProdutoQntd) {
            ItemDePedido item = pq.convertToItemPedido();
            novo.adicionarItem(item);
        }
        return novo;
    }
}
