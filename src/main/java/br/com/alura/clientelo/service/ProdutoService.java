package br.com.alura.clientelo.service;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements ServiceInterface<Long, Produto> {

    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void cadastra(Produto produto) {
        if(produto==null) return;
        this.repository.save(produto);
    }

    @Override
    public Optional<Produto> buscaPorId(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void atualiza(Produto produto) {
        if(produto==null) return;
        this.repository.save(produto);
    }

    @Override
    public List<Produto> listaTodos() {
        return this.repository.findAll();
    }

    public List<Produto> getIndisponiveis(){
        return this.repository.findIndisponiveis();
    }

    public List<Produto> getMaisVendidos(){
        return this.repository.findMaisVendidos();
    }
}