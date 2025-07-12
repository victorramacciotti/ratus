package com.academia.entity.Exercicio; // Sugestão de pacote para DTOs

import com.academia.enums.GrupoMuscular;
import com.academia.enums.TipoEquipamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioRequestDTO {

    @NotBlank(message = "O nome do exercício é obrigatório.")
    @Size(max = 100, message = "O nome do exercício não pode ter mais de 100 caracteres.")
    private String nome;

    @Size(max = 500, message = "A descrição do exercício não pode ter mais de 500 caracteres.")
    private String descricao;

    @NotNull(message = "O tipo de equipamento é obrigatório.")
    private TipoEquipamento tipoEquipamento;

    @NotNull(message = "O grupo muscular principal é obrigatório.")
    private GrupoMuscular grupoMuscularPrincipal;

    @Size(max = 255, message = "A URL do vídeo não pode ter mais de 255 caracteres.")
    private String urlVideoDemonstracao;
}