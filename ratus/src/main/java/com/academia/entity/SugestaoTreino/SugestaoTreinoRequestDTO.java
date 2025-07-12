package com.academia.entity.SugestaoTreino; // Novo pacote para DTOs de SugestaoTreino

import com.academia.enums.DiasDaSemana;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SugestaoTreinoRequestDTO {

    @NotNull(message = "O ID do cliente é obrigatório para a sugestão de treino.")
    private UUID clienteId;

    // Opcional: O dia da semana para o qual a sugestão é desejada.
    // Se nulo, a sugestão pode ser para o próximo dia disponível ou um treino geral.
    private DiasDaSemana diaDaSemana;

    // Futuramente, poderíamos adicionar outros critérios, como:
    // private String focoMuscularDesejado;
    // private Integer nivelIntensidadeDesejado;
}