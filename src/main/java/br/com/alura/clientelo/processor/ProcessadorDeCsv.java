package br.com.alura.clientelo.processor;

import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProcessadorDeCsv implements ProcessadorArquivo{

    @Override
    public List<Pedido> processaArquivo(String nomeDoArquivo) {
        try {
            URL recursoCSV = ClassLoader.getSystemResource(nomeDoArquivo);
            Path caminhoDoArquivo = caminhoDoArquivo = Path.of(recursoCSV.toURI());

            Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);

            leitorDeLinhas.nextLine();

            Pedido[] pedidos = new Pedido[10];
            List<Pedido> listPedidos = new ArrayList<>();

            int quantidadeDeRegistros = 0;
            while (leitorDeLinhas.hasNextLine()) {
                String linha = leitorDeLinhas.nextLine();
                String[] registro = linha.split(",");

                String categoria = registro[0];
                Produto produto = new Produto();
                produto.setNome(registro[1]);
                BigDecimal preco = new BigDecimal(registro[2]);
                int quantidade = Integer.parseInt(registro[3]);
                LocalDate data = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String cliente = registro[5];

                Pedido pedido = new Pedido(categoria, produto, cliente, preco, quantidade, data);
                pedidos[quantidadeDeRegistros] = pedido;
                listPedidos.add(pedido);
                quantidadeDeRegistros++;
                if (pedidos[pedidos.length - 1] != null) {
                    pedidos = Arrays.copyOf(pedidos, pedidos.length * 2);
                }
            }

            return listPedidos;
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Arquivo {} não localizado!", nomeDoArquivo));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao abrir Scanner para processar arquivo!");
        }
    }
}
