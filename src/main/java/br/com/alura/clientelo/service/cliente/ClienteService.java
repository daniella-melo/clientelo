package br.com.alura.clientelo.service.cliente;

import br.com.alura.clientelo.model.cliente.Cliente;
import br.com.alura.clientelo.projecao.cliente.ClienteFielProjecao;
import br.com.alura.clientelo.repository.cliente.ClienteJpaRepository;
import br.com.alura.clientelo.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ServiceInterface<Long, Cliente> {

    @Autowired
    private ClienteJpaRepository repository;

    @Override
    public void cadastra(Cliente cliente) {
        if(cliente==null) return;
        this.repository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscaPorId(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void atualiza(Cliente cliente) {
        if(cliente==null) return;
        this.repository.save(cliente);
    }

    @Override
    public Page<Cliente> listaTodos(Pageable paginacao) {
        return this.repository.findAll(paginacao);
    }

    public List<ClienteFielProjecao> getClientesFieis(){
        return this.repository.findClientesFieis();
    }

    public List<ClienteFielProjecao> getClientesMaisLucrativos(){
        return this.repository.findClientesMaisLucrativos();
    }

    public Optional<Cliente> getById(Long idCliente) {
        return this.repository.findById(idCliente);
    }

    public int getTotalDePedidosDoCliente(Long idCliente){return this.repository.findTotalDePedidos(idCliente);}
}
