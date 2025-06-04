package com.academia.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    // Relacionamentos (inverso)
    @OneToMany(mappedBy = "administrador")
    private List<Cliente> clientesGerenciados;

    @OneToMany(mappedBy = "administrador")
    private List<Instrutor> instrutoresGerenciados;

    // Getters and Setters
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

    public List<Cliente> getClientesGerenciados() {
        return clientesGerenciados;
    }

    public void setClientesGerenciados(List<Cliente> clientesGerenciados) {
        this.clientesGerenciados = clientesGerenciados;
    }

    public List<Instrutor> getInstrutoresGerenciados() {
        return instrutoresGerenciados;
    }

    public void setInstrutoresGerenciados(List<Instrutor> instrutoresGerenciados) {
        this.instrutoresGerenciados = instrutoresGerenciados;
    }
}

