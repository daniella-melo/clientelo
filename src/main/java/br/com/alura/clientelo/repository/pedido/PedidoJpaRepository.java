package br.com.alura.clientelo.repository.pedido;

import br.com.alura.clientelo.model.pedido.Pedido;
import br.com.alura.clientelo.projecao.categoria.VendaPorCategoriaProjecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoJpaRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT " +
            "c.nome, SUM(ip.quantidade), " +
            "SUM(ip.preco_unitario*ip.quantidade) " +
            "FROM item_pedido ip " +
            "JOIN produto p on ip.produto_id = p.id " +
            "JOIN categoria c on p.categoria_id = c.id " +
            "GROUP BY c.nome", nativeQuery = true)
    List<VendaPorCategoriaProjecao> findVendasPorCategoria();
}
