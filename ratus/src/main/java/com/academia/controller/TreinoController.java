package com.academia.controller;

import com.academia.entity.treino.TreinoRequestDTO;
import com.academia.entity.treino.TreinoResponseDTO;
import com.academia.service.TreinoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/treinos") // Endpoint base para treinos
public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping
    public ResponseEntity<TreinoResponseDTO> criarTreino(@RequestBody @Valid TreinoRequestDTO requestDTO) {
        TreinoResponseDTO novoTreino = treinoService.criarTreino(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTreino);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoResponseDTO> buscarTreinoPorId(@PathVariable UUID id) {
        TreinoResponseDTO treino = treinoService.buscarTreinoPorId(id);
        return ResponseEntity.ok(treino);
    }

    @GetMapping
    public ResponseEntity<List<TreinoResponseDTO>> listarTodosTreinos() {
        List<TreinoResponseDTO> treinos = treinoService.listarTodosTreinos();
        return ResponseEntity.ok(treinos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinoResponseDTO> atualizarTreino(@PathVariable UUID id, @RequestBody @Valid TreinoRequestDTO requestDTO) {
        TreinoResponseDTO treinoAtualizado = treinoService.atualizarTreino(id, requestDTO);
        return ResponseEntity.ok(treinoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTreino(@PathVariable UUID id) {
        treinoService.deletarTreino(id);
        return ResponseEntity.noContent().build();
    }
}