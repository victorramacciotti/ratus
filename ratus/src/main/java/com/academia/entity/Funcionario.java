package com.academia.entity;

import java.math.BigDecimal;

import com.academia.enums.EscalaTrabalho;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String telefone;

    @Column(name = "escala_trabalho")
    @Enumerated(EnumType.STRING)
    private EscalaTrabalho escalaTrabalho;

    @Column(precision = 10, scale = 2)
    private BigDecimal salario;
    
    public Funcionario(){}

    /*GETTERS*/
    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public EscalaTrabalho getEscalaTrabalho() {
        return escalaTrabalho;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    /*SETTERS*/
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEscalaTrabalho(EscalaTrabalho escalaTrabalho) {
        this.escalaTrabalho = escalaTrabalho;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}

