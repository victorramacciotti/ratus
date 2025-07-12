package com.academia.entity.exercicioTreino; // Novo pacote para DTOs de ExercicioTreino

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioTreinoRequestDTO {

    @NotNull(message = "O ID do exercício é obrigatório.")
    private UUID exercicioId;

    @Min(value = 1, message = "O número de séries deve ser no mínimo 1.")
    private int series;

    @Min(value = 1, message = "O número de repetições deve ser no mínimo 1.")
    private int repeticoes;

    @Min(value = 0, message = "A carga sugerida não pode ser negativa.")
    private BigDecimal cargaSugeridaKg;

    private String observacoesAdicionais;

    @Min(value = 1, message = "A ordem no treino deve ser no mínimo 1.")
    private int ordemNoTreino;
}