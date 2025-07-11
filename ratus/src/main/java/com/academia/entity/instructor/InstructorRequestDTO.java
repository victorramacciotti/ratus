package com.academia.entity.instructor;

import com.academia.enums.EscalaTrabalho;

import java.math.BigDecimal;
import java.util.UUID;

public record InstructorRequestDTO(
        UUID id,
        String nome,
        String email,
        String telefone,
        String cpf,
        BigDecimal salario,
        EscalaTrabalho escalaTrabalho,
        String especialidade
) {

}
