package br.com.alura.clientelo.service;

import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ServiceInterface<Long, Categoria>{

    @Autowired
    private CategoriaRepository repository;

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
    public List<Categoria> listaTodos() {
       return this.repository.findAll();
    }

    public boolean categoriaValidaParaCadastro(String nome){
        return (this.repository.findByNome(nome) != null && nome.length()>=2);
    }
}
