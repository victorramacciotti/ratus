package com.academia.entity.SugestaoTreino;

import com.academia.entity.treino.TreinoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SugestaoTreinoResponseDTO {
    private String mensagemSugestao; // Uma mensagem explicativa sobre a sugestão
    private List<TreinoResponseDTO> treinosSugeridos; // A lista de treinos sugeridos
}