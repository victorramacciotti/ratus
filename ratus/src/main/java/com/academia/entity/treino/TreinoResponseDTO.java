package com.academia.entity.treino;

import com.academia.enums.DiasDaSemana;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreinoResponseDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private DiasDaSemana diasDaSemana;
}