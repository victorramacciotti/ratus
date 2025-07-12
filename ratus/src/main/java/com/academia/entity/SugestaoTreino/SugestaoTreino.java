package com.academia.entity.SugestaoTreino; // Novo pacote para entidades de SugestaoTreino

import com.academia.entity.treino.Treino;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID; // Se a sugestão em si tivesse um ID único para rastreamento interno

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SugestaoTreino {

    // Embora uma sugestão em si possa não ser persistida, ter um ID pode ser útil
    // para rastreamento interno ou para cenários mais complexos onde sugestões são salvas temporariamente.
    private UUID id;

    private String mensagemSugestao; // Uma mensagem explicativa sobre a sugestão

    // A lista de entidades Treino que estão sendo sugeridas.
    // O serviço irá construir esta lista com base na lógica de sugestão.
    private List<Treino> treinosSugeridos;

    // Construtor sem ID, se o ID for gerado no serviço ou não for sempre necessário
    public SugestaoTreino(String mensagemSugestao, List<Treino> treinosSugeridos) {
        this.mensagemSugestao = mensagemSugestao;
        this.treinosSugeridos = treinosSugeridos;
    }
}