package br.com.alura.clientelo.processor;

import br.com.alura.clientelo.estatisticas.PedidoEstatistica;
import br.com.alura.clientelo.model.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface ProcessadorArquivo {

    Map<Pedido, PedidoEstatistica> processaArquivo(String nomeDoArquivo) ;
}
