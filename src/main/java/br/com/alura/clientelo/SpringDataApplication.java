package br.com.alura.clientelo;

import br.com.alura.clientelo.model.*;
import br.com.alura.clientelo.projecao.ClienteFielProjecao;
import br.com.alura.clientelo.projecao.VendaPorCategoriaProjecao;
import br.com.alura.clientelo.repository.CategoriaRepository;
import br.com.alura.clientelo.repository.ClienteRepository;
import br.com.alura.clientelo.service.CategoriaService;
import br.com.alura.clientelo.service.ClienteService;
import br.com.alura.clientelo.service.PedidoService;
import br.com.alura.clientelo.service.ProdutoService;
import br.com.alura.clientelo.vo.RelatorioClienteFiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class SpringDataApplication implements CommandLineRunner {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoService produtoService;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
//                "cidade fake", "estado fake");
//
//        Cliente cliente1 = new Cliente("Cliente 1", "11111111111", "999999999", endereco);
//        Cliente cliente2 = new Cliente("Cliente 2", "22222222222", "999999999", endereco);
//
//        List<Cliente> todosOsClientes = new ArrayList<>();
//        todosOsClientes.add(cliente1);
//        todosOsClientes.add(cliente2);
//
//        clienteService.cadastra(cliente1);
//        clienteService.cadastra(cliente2);

//        List<ClienteFielProjecao> clientesFieis = clienteService.getClientesFieis();
//        List<ClienteFielProjecao> clientesMaisLucrativos = clienteService.getClientesMaisLucrativos();
//        List<VendaPorCategoriaProjecao> vendaPorCategoriaProjecao = pedidoService.getVendasPorCategoria();

        //    public Produto(String nome, BigDecimal precoUnitario, String descricao, int qntEmEstoque, Categoria categoria) {

        Categoria categoria1 = new Categoria("AUTOMOTIVA", CategoriaStatusEnum.ATIVA);
        categoriaService.cadastra(categoria1);
        Produto produto = new Produto("produto teste", new BigDecimal(10), "descricao", 1, categoria1);
        produtoService.cadastra(produto);
        // public Pedido(Cliente cliente,
        //                  TipoDescontoEnum tipoDesconto)
        Endereco endereco = new Endereco("rua fake", "numero fake", null, "bairro fake",
                "cidade fake", "estado fake");

        Cliente cliente1 = new Cliente("Cliente 1", "11111111111", "999999999", endereco);
        clienteService.cadastra(cliente1);

        Pedido pedido = new Pedido(cliente1, TipoDescontoEnum.NENHUM);
        pedidoService.cadastra(pedido);
    }
}