package br.com.alura.clientelo.repository.categoria;

import br.com.alura.clientelo.model.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaJpaRepository extends JpaRepository<Categoria, Long> {
    Categoria findByNome(String nome);
}
