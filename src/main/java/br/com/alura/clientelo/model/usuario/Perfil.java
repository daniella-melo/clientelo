package br.com.alura.clientelo.model.usuario;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "perfil")
public class Perfil implements GrantedAuthority {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    public Perfil(String nome) {
        this.nome = nome;
    }

    public Perfil() {}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }


    @Override
    public String getAuthority() {
        return this.nome;
    }
}
