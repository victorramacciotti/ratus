package com.academia.entity.FolhaPagamento;

import com.academia.enums.MesReferenteAPagamento; // Importa o novo enum
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FolhaPagamentoRequestDTO {

    @NotNull(message = "O ID do funcionário é obrigatório.")
    private UUID funcionarioId;

    @NotNull(message = "O mês de referência é obrigatório.")
    private MesReferenteAPagamento mesReferencia; // Alterado para o tipo enum

    @Min(value = 2000, message = "O ano de referência deve ser um ano válido.")
    @NotNull(message = "O ano de referência é obrigatório.")
    private int anoReferencia;

    @NotNull(message = "A data de pagamento é obrigatória.")
    private LocalDate dataPagamento;

    @Min(value = 0, message = "O salário base não pode ser negativo.")
    @NotNull(message = "O salário base é obrigatório.")
    private BigDecimal salarioBase;

    @Min(value = 0, message = "O valor total líquido não pode ser negativo.")
    @NotNull(message = "O valor total líquido é obrigatório.")
    private BigDecimal valorTotalLiquido;

    private String observacoes;
}
