package br.com.alura.clientelo.aplicacao.service.pedido;

import br.com.alura.clientelo.aplicacao.repository.pedido.PedidoJpaRepository;
import br.com.alura.clientelo.aplicacao.service.ServiceInterface;
import br.com.alura.clientelo.aplicacao.service.cliente.ClienteService;
import br.com.alura.clientelo.dominio.model.pedido.Pedido;
import br.com.alura.clientelo.dominio.model.pedido.TipoDescontoEnum;
import br.com.alura.clientelo.dominio.projecao.categoria.VendaPorCategoriaProjecao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService implements ServiceInterface<Long, Pedido> {

    @Autowired
    private PedidoJpaRepository repository;
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
    public Page<Pedido> listaTodos(Pageable paginacao) {
        return this.repository.findAll(paginacao);
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
