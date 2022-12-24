package br.com.alura.clientelo.aplicacao.service.produto;

import br.com.alura.clientelo.aplicacao.repository.produto.ProdutoJpaRepository;
import br.com.alura.clientelo.aplicacao.service.ServiceInterface;
import br.com.alura.clientelo.aplicacao.service.categoria.CategoriaService;
import br.com.alura.clientelo.dominio.model.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements ServiceInterface<Long, Produto> {

    @Autowired
    private ProdutoJpaRepository repository;
    @Autowired
    private CategoriaService categoriaService;
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
    public Page<Produto> listaTodos(Pageable paginacao) {
        return this.repository.findAll(paginacao);
    }

    public List<Produto> getIndisponiveis(){
        return this.repository.findIndisponiveis();
    }

    public List<Produto> getMaisVendidos(){
        return this.repository.findMaisVendidos();
    }

    public Optional<Produto> getById(Long idProduto) {
        return this.repository.findById(idProduto);
    }
}
