package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.model.ItemDePedido;
import br.com.alura.clientelo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "SELECT p FROM Produto p WHERE p.qntEmEstoque = 0")
    List<Produto> findIndisponiveis();

    @Query(value = "SELECT p FROM produto p JOIN item_pedido ip on ip.produto_id = p.id " +
            "GROUP BY p.id HAVING SUM(ip.quantidade) > 3", nativeQuery = true)
    List<Produto> findMaisVendidos();
}
