package br.com.alura.clientelo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class Pedido{

    private Long id;
    private String categoria;
    private Cliente cliente;
    private LocalDate data;

    public Pedido(String categoria, Cliente cliente, LocalDate data) {
        if(categoria == null || cliente == null
                || data == null){
            throw new NullPointerException();
        }
        this.categoria = categoria;
        this.cliente = cliente;
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", cliente='" + cliente + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido that = (Pedido) o;
        return Objects.equals(categoria, that.getCategoria()) &&
                Objects.equals(cliente, that.getCliente()) && Objects.equals(data, that.getData());
    }


}
