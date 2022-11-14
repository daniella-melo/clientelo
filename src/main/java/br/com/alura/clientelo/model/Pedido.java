package br.com.alura.clientelo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="pedido")
public class Pedido{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "desconto")
    private String desconto;

    @Column(name = "tipo_desconto", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDescontoEnum tipoDesconto;

    public Pedido(LocalDate data, Cliente cliente, String desconto, TipoDescontoEnum tipoDesconto)  {
        if(cliente == null || data == null){
            throw new NullPointerException();
        }
        this.cliente = cliente;
        this.data = data;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDesconto() {
        return desconto;
    }

    public TipoDescontoEnum getTipoDesconto() {
        return tipoDesconto;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "cliente='" + cliente + '\'' +
                ", data=" + data +
                ", desconto='" + desconto + '\'' +
                ", tipo desconto='" + tipoDesconto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido that = (Pedido) o;
        return Objects.equals(cliente, that.getCliente()) && Objects.equals(data, that.getData());
    }
}
