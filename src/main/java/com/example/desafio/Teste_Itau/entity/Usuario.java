package com.example.desafio.Teste_Itau.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

// A anotação @Data foi removida
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "perc_corretagem", nullable = false)
    private BigDecimal percCorretagem;

    // 1. CONSTRUTOR VAZIO (OBRIGATÓRIO)
    public Usuario() {
    }

    // 2. GETTERS E SETTERS MANUAIS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getPercCorretagem() {
        return percCorretagem;
    }

    public void setPercCorretagem(BigDecimal percCorretagem) {
        this.percCorretagem = percCorretagem;
    }
}