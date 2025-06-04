package com.academia.controller;

import com.academia.entity.TreinoExercicio;
import com.academia.service.TreinoExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/treino-exercicios") // Endpoint para a entidade de ligação
public class TreinoExercicioController {

    @Autowired
    private TreinoExercicioService treinoExercicioService;

    // Endpoint para listar todas as associações (pode não ser muito útil, mas para completude)
    @GetMapping
    public List<TreinoExercicio> getAllTreinoExercicios() {
        return treinoExercicioService.findAll();
    }

    // Endpoint para buscar uma associação específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<TreinoExercicio> getTreinoExercicioById(@PathVariable Long id) {
        Optional<TreinoExercicio> treinoExercicio = treinoExercicioService.findById(id);
        return treinoExercicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para criar uma nova associação (adicionar exercício a um treino)
    @PostMapping
    public TreinoExercicio createTreinoExercicio(@RequestBody TreinoExercicio treinoExercicio) {
        // Validações podem ser adicionadas aqui (ex: verificar se treino e exercício existem)
        return treinoExercicioService.save(treinoExercicio);
    }

    // Endpoint para atualizar detalhes de uma associação (séries, repetições, etc.)
    @PutMapping("/{id}")
    public ResponseEntity<TreinoExercicio> updateTreinoExercicio(@PathVariable Long id, @RequestBody TreinoExercicio treinoExercicioDetails) {
        Optional<TreinoExercicio> optionalTreinoExercicio = treinoExercicioService.findById(id);
        if (optionalTreinoExercicio.isPresent()) {
            TreinoExercicio treinoExercicio = optionalTreinoExercicio.get();
            // Atualizar campos relevantes
            treinoExercicio.setSeries(treinoExercicioDetails.getSeries());
            treinoExercicio.setRepeticoes(treinoExercicioDetails.getRepeticoes());
            treinoExercicio.setTempoDescanso(treinoExercicioDetails.getTempoDescanso());
            // Não permitir alterar treino_id ou exercicio_id aqui, geralmente se remove e adiciona um novo
            return ResponseEntity.ok(treinoExercicioService.save(treinoExercicio));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para remover uma associação (remover exercício de um treino)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreinoExercicio(@PathVariable Long id) {
        if (treinoExercicioService.findById(id).isPresent()) {
            treinoExercicioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoints adicionais podem ser úteis, como buscar todos os exercícios de um treino específico.
}

