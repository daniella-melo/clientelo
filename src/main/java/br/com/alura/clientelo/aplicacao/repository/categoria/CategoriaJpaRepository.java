package br.com.alura.clientelo.aplicacao.repository.categoria;

import br.com.alura.clientelo.dominio.model.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaJpaRepository extends JpaRepository<Categoria, Long> {
    Categoria findByNome(String nome);
}
