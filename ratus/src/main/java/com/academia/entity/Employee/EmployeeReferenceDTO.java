package com.academia.entity.Employee; // Pacote para DTOs de Employee

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReferenceDTO {
    private UUID id;
    private String nome;
    private String cpf; // Opcional: incluir CPF se for uma informação essencial para referência
}
