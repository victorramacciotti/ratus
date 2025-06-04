package com.academia.controller;

import com.academia.entity.Plano;
import com.academia.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planos")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    @GetMapping
    public List<Plano> getAllPlanos() {
        return planoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> getPlanoById(@PathVariable Long id) {
        Optional<Plano> plano = planoService.findById(id);
        return plano.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Plano createPlano(@RequestBody Plano plano) {
        return planoService.save(plano);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plano> updatePlano(@PathVariable Long id, @RequestBody Plano planoDetails) {
        Optional<Plano> optionalPlano = planoService.findById(id);
        if (optionalPlano.isPresent()) {
            Plano plano = optionalPlano.get();
            plano.setTipo(planoDetails.getTipo());
            plano.setValor(planoDetails.getValor());
            return ResponseEntity.ok(planoService.save(plano));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlano(@PathVariable Long id) {
        if (planoService.findById(id).isPresent()) {
            planoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

