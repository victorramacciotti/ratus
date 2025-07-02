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

    public AvaliacaoFisica(){}

    /*GETTERS*/
    public Long getId() {
        return id;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public BigDecimal getAltura() {
        return altura;
    }
    
    public BigDecimal getImc() {
        return imc;
    }
    /*SETTERS*/
    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public void setImc(BigDecimal imc) {
        this.imc = imc;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
