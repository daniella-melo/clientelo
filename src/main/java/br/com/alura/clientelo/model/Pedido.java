package br.com.alura.clientelo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemDePedido> listItemDePedido;

    @Column(name = "desconto", nullable = false, scale = 2)
    private BigDecimal desconto;

    @Column(name = "tipo_desconto", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDescontoEnum tipoDesconto;

    public Pedido(Cliente cliente, BigDecimal desconto,
                  TipoDescontoEnum tipoDesconto)  {
        if(cliente == null){
            throw new NullPointerException();
        }
        this.cliente = cliente;
        this.data = LocalDate.now();
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
        this.listItemDePedido = new ArrayList<>();
    }

    public void adicionarItem(ItemDePedido item){
        item.setPedido(this);
        this.listItemDePedido.add(item);
    }
    public Long getId() {
        return id;
    }

    public List<ItemDePedido> getListItemDePedido() {
        return listItemDePedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getDesconto() {
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
