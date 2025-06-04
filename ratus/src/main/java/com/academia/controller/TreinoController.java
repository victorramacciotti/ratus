package com.academia.controller;

import com.academia.entity.Treino;
import com.academia.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/treinos")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @GetMapping
    public List<Treino> getAllTreinos() {
        return treinoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treino> getTreinoById(@PathVariable Long id) {
        Optional<Treino> treino = treinoService.findById(id);
        return treino.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Treino createTreino(@RequestBody Treino treino) {
        // Adicionar lógica para associar exercícios ao treino, se necessário
        return treinoService.save(treino);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Treino> updateTreino(@PathVariable Long id, @RequestBody Treino treinoDetails) {
        Optional<Treino> optionalTreino = treinoService.findById(id);
        if (optionalTreino.isPresent()) {
            Treino treino = optionalTreino.get();
            treino.setCliente(treinoDetails.getCliente()); // Assumindo que o ID do cliente vem no request
            treino.setDataInicio(treinoDetails.getDataInicio());
            treino.setDataFim(treinoDetails.getDataFim());
            treino.setObservacoes(treinoDetails.getObservacoes());
            // Lógica para atualizar TreinoExercicios pode ser mais complexa
            return ResponseEntity.ok(treinoService.save(treino));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreino(@PathVariable Long id) {
        if (treinoService.findById(id).isPresent()) {
            treinoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoints adicionais podem ser necessários, como para buscar treinos de um cliente específico
    // ou para adicionar/remover exercícios de um treino existente.
}

