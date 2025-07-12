package com.academia.entity.FolhaPagamento;

import com.academia.entity.Employee.Employee;
import com.academia.enums.MesReferenteAPagamento; // Certifique-se de que este import está correto
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "folha_pagamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FolhaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_folha_pagamento") // Mapeia para id_folha_pagamento na tabela
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false) // Mapeia para id_funcionario na tabela
    private Employee funcionario;

    @Enumerated(EnumType.STRING)
    @Column(name = "mes_referencia", nullable = false) // Mapeia para mes_referencia na tabela
    private MesReferenteAPagamento mesReferencia;

    @Column(name = "ano_referencia", nullable = false) // Mapeia para ano_referencia na tabela
    private Integer anoReferencia;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;

    @Column(name = "salario_base", precision = 10, scale = 2, nullable = false) // Mapeia para salario_base
    private BigDecimal salarioBase;

    @Column(name = "valor_total_liquido", precision = 10, scale = 2, nullable = false) // Mapeia para valor_total_liquido
    private BigDecimal valorTotalLiquido;

    @Column(name = "observacoes", length = 500)
    private String observacoes;
}
