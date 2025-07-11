package com.academia.controller;

import com.academia.entity.instructor.Instructor;
import com.academia.entity.instructor.InstructorRequestDTO;
import com.academia.entity.instructor.InstructorResponseDTO;
import com.academia.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("instrutores")
public class InstrutorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<InstructorResponseDTO>> getAllInstructors() {
        List<Instructor> allInstructors = this.instructorService.findAllInstructors();
        List<InstructorResponseDTO> responseDTOs = allInstructors.stream()
                .map(InstructorResponseDTO::fromEntity) // Usando um metodo estático na DTO de resposta
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponseDTO> getInstructorById(@PathVariable("id") UUID id) {
        return this.instructorService.findById(id) // Supondo que findById agora retorna Optional<Instructor>
                .map(InstructorResponseDTO::fromEntity) // Converte Instructor para DTO se presente
                .map(ResponseEntity::ok) // Envolve o DTO em um ResponseEntity.ok()
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 se o Optional estiver vazio
    }

    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody InstructorRequestDTO body) {
        Instructor newInstructor = this.instructorService.createInstrutor(body);

        // Retornando 201 Created com a URI do novo recurso
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newInstructor.getId())
                .toUri();

        return ResponseEntity.created(uri).body(newInstructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable("id") UUID id) {
        boolean deleted = instructorService.deleteInstructor(id); // O serviço agora retorna um boolean
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}