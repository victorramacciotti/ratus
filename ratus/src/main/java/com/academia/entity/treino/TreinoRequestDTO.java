package com.academia.entity.treino;

import com.academia.entity.exercicioTreino.ExercicioTreinoRequestDTO;
import com.academia.enums.DiasDaSemana;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreinoRequestDTO {

    @NotBlank(message = "O nome do treino é obrigatório.")
    @Size(max = 255, message = "O nome do treino não pode ter mais de 255 caracteres.")
    private String nome;

    @Size(max = 1000, message = "A descrição do treino não pode ter mais de 1000 caracteres.")
    private String descricao;

    @NotNull(message = "O dia da semana é obrigatório.")
    private DiasDaSemana diasDaSemana;

    @NotNull(message = "O ID do instrutor é obrigatório.")
    private UUID instrutorId;

    @NotNull(message = "O ID do cliente é obrigatório.")
    private UUID clienteId;

    @NotNull(message = "A lista de exercícios do treino não pode ser nula.")
    @Size(min = 1, message = "Um treino deve conter pelo menos um exercício.")
    private List<ExercicioTreinoRequestDTO> exerciciosDoTreino;
}