package com.academia.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "instrutor")
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String especialidade;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
}

