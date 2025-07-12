package com.academia.entity.treino;

import com.academia.entity.Employee.instructor.InstructorResponseDTO;
import com.academia.entity.cliente.ClienteReferenceDTO;
import com.academia.entity.exercicioTreino.ExercicioTreinoResponseDTO;
import com.academia.enums.DiasDaSemana;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreinoResponseDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private DiasDaSemana diasDaSemana;
    private InstructorResponseDTO instrutor; // Seu DTO padrão de Instrutor
    private ClienteReferenceDTO cliente;     // DTO de referência para Cliente (para evitar loop)
    private List<ExercicioTreinoResponseDTO> exerciciosDoTreino; // Lista de exercícios detalhados do treino
}