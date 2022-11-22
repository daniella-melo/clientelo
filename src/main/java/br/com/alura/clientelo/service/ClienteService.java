package br.com.alura.clientelo.service;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.projecao.ClienteFielProjecao;
import br.com.alura.clientelo.repository.ClienteRepository;
import br.com.alura.clientelo.vo.RelatorioClienteFiel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ServiceInterface<Long, Cliente>{

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

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
    public List<Cliente> listaTodos() {
        return this.repository.findAll();
    }

    public List<ClienteFielProjecao> getClientesFieis(){
        return this.repository.findClientesFieis();
    }

    public List<ClienteFielProjecao> getClientesMaisLucrativos(){
        return this.repository.findClientesMaisLucrativos();
    }
}
