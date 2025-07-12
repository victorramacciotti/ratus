package com.academia.entity.ExecucaoExercicio; // Novo pacote para DTOs de ExecucaoExercicio

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecucaoExercicioRequestDTO {

    @NotNull(message = "O ID do cliente é obrigatório.")
    private UUID clienteId;

    @NotNull(message = "O ID do exercício do treino é obrigatório.")
    private UUID exercicioTreinoId;

    @NotNull(message = "A data e hora da execução são obrigatórias.")
    private LocalDateTime dataExecucao;

    @Min(value = 0, message = "O número de séries realizadas não pode ser negativo.")
    private int seriesRealizadas;

    @Min(value = 0, message = "O número de repetições realizadas não pode ser negativo.")
    private int repeticoesRealizadas;

    @Min(value = 0, message = "A carga realizada não pode ser negativa.")
    private BigDecimal cargaRealizadaKg;

    private String observacoes;
}