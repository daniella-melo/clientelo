package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.vo.RelatorioClienteFiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNome(String nome);

    @Query(value = "SELECT new br.com.alura.clientelo.vo.RelatorioClienteFiel (c.nome, COUNT(p.id), " +
            "SUM(ip.precoUnitario * ip.quantidade)) rc " +
            "FROM Cliente c " +
            "JOIN Pedido p on p.cliente = c " +
            "JOIN ItemDePedido ip on ip.pedido = p " +
            "GROUP BY c.nome ORDER BY COUNT(p.id) DESC", nativeQuery = true)
    List<RelatorioClienteFiel> findClientesFieis();


    @Query(value = "SELECT new br.com.alura.clientelo.vo.RelatorioClienteFiel (" +
            "c.nome, COUNT(p.id), " +
            "SUM(ip.precoUnitario * ip.quantidade)) rc " +
            "FROM CLiente c " +
            "JOIN Pedido p on p.cliente = c " +
            "JOIN ItemDePedido ip on ip.pedido = p " +
            "GROUP BY c.nome ORDER BY SUM(ip.precoUnitario * ip.quantidade) DESC LIMIT 2", nativeQuery = true)
    List<RelatorioClienteFiel> findClientesMaisLucrativos();
}
