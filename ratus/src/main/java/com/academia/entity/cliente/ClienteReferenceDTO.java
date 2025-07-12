package com.academia.entity.cliente; // Pacote para DTOs de Cliente

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteReferenceDTO {
    private UUID id;
    private String nome;
}