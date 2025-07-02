package com.academia.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exercicio_treino")
public class ExercicioTreino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "treino_id", nullable = false)
    private Treino treino;

    @ManyToOne
    @JoinColumn(name = "exercicio_id", nullable = false)
    private Exercicio exercicio;

    public ExercicioTreino(){}

    /*GETTERS*/
    public Long getId() {
        return id;
    }

    public Treino getTreino() {
        return treino;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    /*SETTERS*/
    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }
}

