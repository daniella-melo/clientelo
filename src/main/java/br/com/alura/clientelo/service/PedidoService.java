package br.com.alura.clientelo.service;

import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.TipoDescontoEnum;
import br.com.alura.clientelo.projecao.VendaPorCategoriaProjecao;
import br.com.alura.clientelo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService implements ServiceInterface<Long, Pedido>{

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ClienteService clienteService;

    @Override
    public void cadastra(Pedido pedido) {
        if(pedido==null) return;
        this.repository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscaPorId(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void atualiza(Pedido pedido) {
        if(pedido==null) return;
        this.repository.save(pedido);
    }

    @Override
    public List<Pedido> listaTodos() {
        return this.repository.findAll();
    }

    public List<VendaPorCategoriaProjecao> getVendasPorCategoria(){
        return this.repository.findVendasPorCategoria();
    }

    public void aplicarDescontos(Pedido pedido){
        int qntPedidosDoCliente = clienteService.getTotalDePedidosDoCliente(pedido.getCliente().getId());

        if(qntPedidosDoCliente > 5){
            pedido.aplicarDesconto(TipoDescontoEnum.FIDELIDADE);
        }else{
            pedido.aplicarDesconto(TipoDescontoEnum.NENHUM);
        }
    }

    public Optional<Pedido> getById(Long id) {
        return this.repository.findById(id);
    }
}
