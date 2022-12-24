package br.com.alura.clientelo.aplicacao.repository.cliente;

import br.com.alura.clientelo.dominio.model.cliente.Cliente;
import br.com.alura.clientelo.dominio.projecao.cliente.ClienteFielProjecao;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan
public interface ClienteJpaRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNome(String nome);

    @Query(value = "SELECT c.nome, COUNT(p.id), " +
            "SUM(ip.preco_unitario * ip.quantidade) " +
            "FROM cliente c " +
            "JOIN pedido p on p.cliente_id = c.id " +
            "JOIN item_pedido ip on ip.pedido_id = p.id " +
            "GROUP BY c.nome ORDER BY COUNT(p.id) DESC", nativeQuery = true)
    List<ClienteFielProjecao> findClientesFieis();

    @Query(value = "SELECT " +
            "c.nome, COUNT(p.id), " +
            "SUM(ip.preco_unitario * ip.quantidade) " +
            "FROM cliente c " +
            "JOIN pedido p on p.cliente_id = c.id " +
            "JOIN item_pedido ip on ip.pedido_id = p.id " +
            "GROUP BY c.nome ORDER BY SUM(ip.preco_unitario * ip.quantidade) DESC LIMIT 2", nativeQuery = true)
    List<ClienteFielProjecao> findClientesMaisLucrativos();

    @Query(value = "SELECT count(p.cliente_id) " +
            "FROM pedido p " +
            "WHERE p.cliente_id = :idCliente " +
            "GROUP BY p.cliente_id", nativeQuery = true)
    int findTotalDePedidos(Long idCliente);
}
