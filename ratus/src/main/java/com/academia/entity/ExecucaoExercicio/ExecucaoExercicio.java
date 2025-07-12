package com.academia.entity.ExecucaoExercicio;

import com.academia.entity.cliente.Cliente;
import com.academia.entity.exercicioTreino.ExercicioTreino;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "execucao_exercicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecucaoExercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_execucao_exercicio", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exercicio_treino", nullable = false)
    private ExercicioTreino exercicioTreino;

    @Column(name = "data_execucao", nullable = false)
    private LocalDateTime dataExecucao;

    @Column(name = "series_realizadas", nullable = false)
    private int seriesRealizadas;

    @Column(name = "repeticoes_realizadas", nullable = false)
    private int repeticoesRealizadas;

    @Column(name = "carga_realizada_kg", precision = 10, scale = 2)
    private BigDecimal cargaRealizadaKg;

    @Column(name = "observacoes", length = 500)
    private String observacoes;
}