package com.academia.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Exercicio")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @OneToMany(mappedBy = "exercicio")
    private List<TreinoExercicio> treinoExercicios;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<TreinoExercicio> getTreinoExercicios() {
        return treinoExercicios;
    }

    public void setTreinoExercicios(List<TreinoExercicio> treinoExercicios) {
        this.treinoExercicios = treinoExercicios;
    }
}

