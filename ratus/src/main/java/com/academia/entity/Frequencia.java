package com.academia.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "frequencia")
public class Frequencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private boolean presenca;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Frequencia(){}

    /*GETTERS*/
    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean getPresenca(){
        return presenca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    /*SETTERS*/
    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setPresenca(boolean presenca) {
        this.presenca = presenca;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
