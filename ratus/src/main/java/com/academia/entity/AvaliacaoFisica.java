package com.academia.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "avaliacao_fisica")
public class AvaliacaoFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 3, scale = 2)
    private BigDecimal peso;

    @Column(precision = 3, scale = 2)
    private BigDecimal altura;

    @Column(precision = 3, scale = 2)
    private BigDecimal imc;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
