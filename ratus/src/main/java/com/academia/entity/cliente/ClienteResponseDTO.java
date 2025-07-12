package com.academia.entity.cliente;

import com.academia.entity.treino.TreinoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {

    private UUID id;
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private List<TreinoResponseDTO> treinos;
}