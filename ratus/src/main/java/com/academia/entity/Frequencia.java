package com.academia.entity;

import java.time.LocalDate;

import com.academia.entity.cliente.Cliente;
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
}
