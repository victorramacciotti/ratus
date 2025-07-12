package com.academia.entity.Exercicio; // Sugestão de pacote para DTOs

import com.academia.enums.GrupoMuscular;
import com.academia.enums.TipoEquipamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioResponseDTO {

    private UUID id;
    private String nome;
    private String descricao;
    private TipoEquipamento tipoEquipamento;
    private GrupoMuscular grupoMuscularPrincipal;
}