package com.academia.entity.treino; // Pacote para DTOs de Treino

import com.academia.enums.DiasDaSemana;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreinoReferenceDTO {
    private UUID id;
    private String nome;
    private DiasDaSemana diasDaSemana;
    // Opcional: Adicionar ID do instrutor ou cliente se for relevante para a referência,
    // mas evite aninhar DTOs completos para evitar loops.
    // private UUID instrutorId;
    // private UUID clienteId;
}