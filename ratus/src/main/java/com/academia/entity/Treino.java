package com.academia.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "treino")
public class Treino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

    public Treino(){}

    /*GETTERS*/
    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    /*SETTERS*/
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }
}

