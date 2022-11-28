package br.com.alura.clientelo;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Endereco;
import br.com.alura.clientelo.projecao.ClienteFielProjecao;
import br.com.alura.clientelo.projecao.VendaPorCategoriaProjecao;
import br.com.alura.clientelo.repository.CategoriaRepository;
import br.com.alura.clientelo.repository.ClienteRepository;
import br.com.alura.clientelo.service.CategoriaService;
import br.com.alura.clientelo.service.ClienteService;
import br.com.alura.clientelo.service.PedidoService;
import br.com.alura.clientelo.vo.RelatorioClienteFiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoService pedidoService;

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
    }
}