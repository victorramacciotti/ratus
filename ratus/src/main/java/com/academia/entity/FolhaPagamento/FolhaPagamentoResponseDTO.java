package com.academia.entity.FolhaPagamento;

import com.academia.entity.Employee.EmployeeReferenceDTO;
import com.academia.enums.MesReferenteAPagamento; // Importa o novo enum
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FolhaPagamentoResponseDTO {
    private UUID id;
    private EmployeeReferenceDTO funcionario;
    private MesReferenteAPagamento mesReferencia; // Alterado para o tipo enum
    private int anoReferencia;
    private LocalDate dataPagamento;
    private BigDecimal salarioBase;
    private BigDecimal valorTotalLiquido;
    private String observacoes;
}
