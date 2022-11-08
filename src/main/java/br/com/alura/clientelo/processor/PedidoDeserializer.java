package br.com.alura.clientelo.processor;

import br.com.alura.clientelo.model.Pedido;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class PedidoDeserializer {
        @JsonProperty("categoria")
        private String categoria;
        @JsonProperty("produto")
        private String produto;
        @JsonProperty("cliente")
        private String cliente;

        @JsonProperty("preco")
        private BigDecimal preco;
        @JsonProperty("quantidade")
        private int quantidade;

        @JsonProperty("data")
        @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
        private LocalDate data;

        private BigDecimal valorTotal;

        public PedidoDeserializer() {
        }

        @JsonCreator
        public PedidoDeserializer(String categoria,String produto, String cliente,
                      BigDecimal preco,int quantidade,LocalDate data) {
            if(categoria == null || produto == null || cliente == null
                    || preco == null || quantidade == 0 || data == null){
                throw new NullPointerException();
            }
            this.categoria = categoria;
            this.produto = produto;
            this.cliente = cliente;
            this.preco = preco;
            this.quantidade = quantidade;
            this.data = data;
        }

        public Pedido toPedido() {
            return new Pedido(categoria,produto,cliente,preco,quantidade,data);
        }

        public String getCategoria() {
            return categoria;
        }

        public String getProduto() {
            return produto;
        }

        public String getCliente() {
            return cliente;
        }

        public BigDecimal getPreco() {
            return preco;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public LocalDate getData() {
            return data;
        }
}
