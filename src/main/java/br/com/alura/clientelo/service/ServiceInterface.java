package br.com.alura.clientelo.service;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ServiceInterface <K,T>{
    void cadastra(T t);
    Optional<T> buscaPorId(K k);
    void atualiza(T t);
    List<T> listaTodos();
}
