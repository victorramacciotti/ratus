package com.academia.controller;

import com.academia.entity.SugestaoTreino.SugestaoTreinoRequestDTO;
import com.academia.entity.SugestaoTreino.SugestaoTreinoResponseDTO;
import com.academia.service.SugestaoTreinoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sugestoes-treinos") // Endpoint base para sugestões de treinos
public class SugestaoTreinoController {

    private final SugestaoTreinoService sugestaoTreinoService;

    public SugestaoTreinoController(SugestaoTreinoService sugestaoTreinoService) {
        this.sugestaoTreinoService = sugestaoTreinoService;
    }

    /**
     * Gera e retorna uma sugestão de treino personalizada para um cliente.
     * Apenas usuários com as roles ADMIN ou INSTRUTOR podem realizar esta operação.
     * @param requestDTO Contém o ID do cliente e, opcionalmente, o dia da semana para a sugestão.
     * @return ResponseEntity com o SugestaoTreinoResponseDTO contendo a sugestão e uma mensagem, e status 200 OK.
     */
    @PostMapping
    public ResponseEntity<SugestaoTreinoResponseDTO> sugerirTreino(@RequestBody @Valid SugestaoTreinoRequestDTO requestDTO) {
        SugestaoTreinoResponseDTO sugestao = sugestaoTreinoService.sugerirTreino(requestDTO);
        return ResponseEntity.ok(sugestao);
    }

    // Futuramente, outros endpoints podem ser adicionados aqui para refinar a sugestão,
    // como buscar sugestões por nível de dificuldade, grupo muscular, etc.
}