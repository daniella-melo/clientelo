package br.com.alura.clientelo.aplicacao.service.categoria;

import br.com.alura.clientelo.aplicacao.repository.categoria.CategoriaJpaRepository;
import br.com.alura.clientelo.aplicacao.service.ServiceInterface;
import br.com.alura.clientelo.dominio.model.categoria.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService implements ServiceInterface<Long, Categoria> {

    @Autowired
    private CategoriaJpaRepository repository;

    @Override
    public void cadastra(Categoria categoria) {
        if(categoria==null) return;
        this.repository.save(categoria);
    }

    @Override
    public Optional<Categoria> buscaPorId(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void atualiza(Categoria categoria) {
        if(categoria==null) return;
        this.repository.save(categoria);
    }

    @Override
    public Page<Categoria> listaTodos(Pageable paginacao) {
       return this.repository.findAll(paginacao);
    }

    public Optional<Categoria> getById(Long id) {
        return repository.findById(id);
    }
}
