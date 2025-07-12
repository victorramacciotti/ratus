package com.academia.entity.Employee.instructor;

import com.academia.enums.EscalaTrabalho;

import java.math.BigDecimal;
import java.util.UUID;

public record InstructorResponseDTO(
        UUID id,
        String cpf,
        String nome,
        String email,
        String telefone,
        EscalaTrabalho escalaTrabalho,
        BigDecimal salario,
        String especialidade
) {

    public static InstructorResponseDTO fromEntity(Instructor instructor) {
        return new InstructorResponseDTO(
                instructor.getId(),
                instructor.getCpf(),
                instructor.getNome(),
                instructor.getEmail(),
                instructor.getTelefone(),
                instructor.getEscalaTrabalho(),
                instructor.getSalario(),
                instructor.getEspecialidade()
        );
    }
}
