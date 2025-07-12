package com.academia.entity.Employee;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
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
    @Column(name = "id_funcionario")
    private UUID id;

    @CPF
    @Column(name = "cpf_funcionario", nullable = false, unique = true)
    private String cpf;

    @Column(name = "nome_funcionario", nullable = false)
    private String nome;

    @Email
    @Column(name = "email_funcionario", nullable = false, unique = true)
    private String email;

    @Column(name = "telefone_funcionario")
    private String telefone;

    @Column(name = "escala_trabalho")
    @Enumerated(EnumType.STRING)
    private EscalaTrabalho escalaTrabalho;

    @Column(name = "salario_funcionario", precision = 10, scale = 2)
    private BigDecimal salario;
}