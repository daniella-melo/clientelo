package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.model.ItemDePedido;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.vo.RelatorioVendasPorCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT new br.com.alura.clientelo.vo.RelatorioVendasPorCategoria(" +
            "ip.produto.categoria.nome, SUM(ip.quantidade), " +
            "SUM(ip.precoUnitario*ip.quantidade)) rp " +
            "FROM ItemDePedido ip " +
            "GROUP BY ip.produto.categoria.nome",nativeQuery = true)
    List<RelatorioVendasPorCategoria> findVendasPorCategoria();
}
