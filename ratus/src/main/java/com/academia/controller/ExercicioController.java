package com.academia.controller;

import com.academia.entity.Exercicio;
import com.academia.service.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @GetMapping
    public List<Exercicio> getAllExercicios() {
        return exercicioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> getExercicioById(@PathVariable Long id) {
        Optional<Exercicio> exercicio = exercicioService.findById(id);
        return exercicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Exercicio createExercicio(@RequestBody Exercicio exercicio) {
        return exercicioService.save(exercicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercicio> updateExercicio(@PathVariable Long id, @RequestBody Exercicio exercicioDetails) {
        Optional<Exercicio> optionalExercicio = exercicioService.findById(id);
        if (optionalExercicio.isPresent()) {
            Exercicio exercicio = optionalExercicio.get();
            exercicio.setNome(exercicioDetails.getNome());
            exercicio.setDescricao(exercicioDetails.getDescricao());
            // Atualizar outros campos se necessário
            return ResponseEntity.ok(exercicioService.save(exercicio));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercicio(@PathVariable Long id) {
        if (exercicioService.findById(id).isPresent()) {
            exercicioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

