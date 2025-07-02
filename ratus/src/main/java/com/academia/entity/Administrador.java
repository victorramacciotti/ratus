package com.academia.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public Administrador(){}

    /*GETTERS*/
    public Long getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    /*SETTERS*/
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

}

