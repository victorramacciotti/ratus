package com.academia.entity;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.br.CPF;

import com.academia.enums.EscalaTrabalho;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CPF
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Email
    @Column(nullable = false, unique = true)
    private String email;
    
    private String telefone;

    @Column(name = "escala_trabalho")
    @Enumerated(EnumType.STRING)
    private EscalaTrabalho escalaTrabalho;

    @Column(precision = 10, scale = 2)
    private BigDecimal salario;
}

