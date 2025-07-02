package com.academia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "treino_cliente")
public class TreinoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diasDaSemana;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    private Treino treino;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public TreinoCliente(){}

    /*GETTERS*/
    public Long getId() {
        return id;
    }

    public String getDiasDaSemana() {
        return diasDaSemana;
    }

    public Treino getTreino() {
        return treino;
    }

    public Cliente getCliente() {
        return cliente;
    }
    /*SETTERS*/
    public void setDiasDaSemana(String diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
