package com.academia.controller; // Sugestão de pacote para Controllers

import com.academia.entity.Exercicio.ExercicioRequestDTO;
import com.academia.entity.Exercicio.ExercicioResponseDTO;
import com.academia.service.ExercicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercicios") // Endpoint base para exercícios
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;


    @GetMapping
    public ResponseEntity<List<ExercicioResponseDTO>> listarTodosExercicios() {
        List<ExercicioResponseDTO> exercicios = exercicioService.listarTodosExercicios();
        return ResponseEntity.ok(exercicios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExercicioResponseDTO> buscarExercicioPorId(@PathVariable UUID id) {
        ExercicioResponseDTO exercicio = exercicioService.buscarExercicioPorId(id);
        return ResponseEntity.ok(exercicio);
    }

    @PostMapping
    public ResponseEntity<ExercicioResponseDTO> criarExercicio(@RequestBody @Valid ExercicioRequestDTO requestDTO) {
        ExercicioResponseDTO novoExercicio = exercicioService.criarExercicio(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoExercicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExercicioResponseDTO> atualizarExercicio(@PathVariable UUID id, @RequestBody @Valid ExercicioRequestDTO requestDTO) {
        ExercicioResponseDTO exercicioAtualizado = exercicioService.atualizarExercicio(id, requestDTO);
        return ResponseEntity.ok(exercicioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExercicio(@PathVariable UUID id) {
        exercicioService.deletarExercicio(id);
        return ResponseEntity.noContent().build();
    }
}