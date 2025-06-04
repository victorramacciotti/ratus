package com.academia.controller;

import com.academia.entity.Instrutor;
import com.academia.service.InstrutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instrutores")
public class InstrutorController {

    @Autowired
    private InstrutorService instrutorService;

    @GetMapping
    public List<Instrutor> getAllInstrutores() {
        return instrutorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrutor> getInstrutorById(@PathVariable Long id) {
        Optional<Instrutor> instrutor = instrutorService.findById(id);
        return instrutor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Instrutor createInstrutor(@RequestBody Instrutor instrutor) {
        return instrutorService.save(instrutor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instrutor> updateInstrutor(@PathVariable Long id, @RequestBody Instrutor instrutorDetails) {
        Optional<Instrutor> optionalInstrutor = instrutorService.findById(id);
        if (optionalInstrutor.isPresent()) {
            Instrutor instrutor = optionalInstrutor.get();
            instrutor.setNome(instrutorDetails.getNome());
            instrutor.setEspecialidade(instrutorDetails.getEspecialidade());
            instrutor.setAdministrador(instrutorDetails.getAdministrador()); // Assumindo que o ID do admin vem no request
            return ResponseEntity.ok(instrutorService.save(instrutor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstrutor(@PathVariable Long id) {
        if (instrutorService.findById(id).isPresent()) {
            instrutorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

