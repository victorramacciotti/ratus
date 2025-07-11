package com.academia.entity.Employee;

import java.math.BigDecimal;
import java.util.UUID; // <-- CORREÇÃO: Importe o UUID do pacote java.util

import jakarta.persistence.*;
import lombok.*;
// import org.hibernate.validator.constraints.UUID; // <-- REMOVA ESTA IMPORTAÇÃO
import org.hibernate.validator.constraints.br.CPF;

import com.academia.enums.EscalaTrabalho;

import jakarta.validation.constraints.Email;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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