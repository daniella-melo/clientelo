package br.com.alura.clientelo.processor;

import br.com.alura.clientelo.model.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ProcessadorArquivo {

    List<Pedido> processaArquivo(String nomeDoArquivo) ;
}
