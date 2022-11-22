package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.projecao.ClienteFielProjecao;
import br.com.alura.clientelo.vo.RelatorioClienteFiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNome(String nome);

    @Query(value = "SELECT c.nome, COUNT(p.id), " +
            "SUM(ip.precoUnitario * ip.quantidade) " +
            "FROM Cliente c " +
            "JOIN Pedido p on p.cliente = c " +
            "JOIN ItemDePedido ip on ip.pedido = p " +
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
}
