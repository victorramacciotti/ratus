package com.academia.entity.ExecucaoExercicio;

import com.academia.entity.Exercicio.ExercicioResponseDTO;
import com.academia.entity.cliente.ClienteReferenceDTO;
import com.academia.entity.treino.TreinoReferenceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecucaoExercicioResponseDTO {
    private UUID id;
    private ClienteReferenceDTO cliente;
    private TreinoReferenceDTO treino;
    private ExercicioResponseDTO exercicio;
    private LocalDateTime dataExecucao;
    private int seriesRealizadas;
    private int repeticoesRealizadas;
    private BigDecimal cargaRealizadaKg;
    private String observacoes;
}