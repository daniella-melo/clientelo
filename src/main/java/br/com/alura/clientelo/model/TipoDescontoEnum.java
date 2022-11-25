package br.com.alura.clientelo.model;

import java.math.BigDecimal;

public enum TipoDescontoEnum {
    QUANTIDADE{
        public BigDecimal desconto(){
            return new BigDecimal(0.1);
        }
    },
    PROMOCAO{
        public BigDecimal desconto(){
            return BigDecimal.ZERO;
        }
    },
    NENHUM{
        public BigDecimal desconto(){
            return BigDecimal.ZERO;
        }
    },
    FIDELIDADE{
        public BigDecimal desconto(){
            return new BigDecimal(0.05);
        }
    };

    public abstract BigDecimal desconto();
}
