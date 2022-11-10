package br.com.alura.clientelo.processor;

import br.com.alura.clientelo.estatisticas.PedidoEstatistica;
import br.com.alura.clientelo.model.Pedido;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessadorDeJson implements ProcessadorArquivo {
    //TODO: ajeitar o método para retornar um map
    @Override
    public Map<Pedido, PedidoEstatistica> processaArquivo(String nomeDoArquivo){
        try {
            if (nomeDoArquivo == null) {
                throw new NullPointerException("Nome de arquivo inválido");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.registerModule(new JavaTimeModule());

            URL recursoJson = ClassLoader.getSystemResource(nomeDoArquivo);
            File file = new File(recursoJson.toURI());
            TypeReference<List<PedidoDeserializer>> mapType = new TypeReference<>() {
            };
            List<PedidoDeserializer> listPedidosDeserializer = objectMapper.readValue(file, mapType);
            List<Pedido> listPedidos = new ArrayList<>();
            for (PedidoDeserializer p : listPedidosDeserializer) {
                listPedidos.add(p.toPedido());
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar arquivo!");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
