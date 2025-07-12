package com.academia.entity.exercicioTreino;

import com.academia.entity.Exercicio.ExercicioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioTreinoResponseDTO {
    private UUID id;
    private ExercicioResponseDTO exercicio;
    private int series;
    private int repeticoes;
    private BigDecimal cargaSugeridaKg;
    private String observacoesAdicionais;
    private int ordemNoTreino;
}