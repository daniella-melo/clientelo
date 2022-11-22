package br.com.alura.clientelo;

import br.com.alura.clientelo.projecao.ClienteFielProjecao;
import br.com.alura.clientelo.repository.CategoriaRepository;
import br.com.alura.clientelo.repository.ClienteRepository;
import br.com.alura.clientelo.vo.RelatorioClienteFiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private CategoriaRepository categoriaRepository;
    private ClienteRepository clienteRepository;

    public SpringDataApplication(CategoriaRepository categoriaRepository, ClienteRepository clienteRepository){
        this.categoriaRepository = categoriaRepository;
        this.clienteRepository = clienteRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<ClienteFielProjecao> clientesFieis = clienteRepository.findClientesFieis();
        List<ClienteFielProjecao> clientesMaisLucrativos = clienteRepository.findClientesMaisLucrativos();
    }
}