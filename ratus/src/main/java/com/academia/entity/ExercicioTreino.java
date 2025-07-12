package com.academia.entity;

import com.academia.entity.Exercicio.Exercicio;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "exercicio_treino")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class ExercicioTreino {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_exercicio_treino")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_treino", nullable = false)
    private Treino treino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exercicio", nullable = false)
    private Exercicio exercicio;

    @Column(name = "quantidade_series", nullable = false)
    private int series;

    @Column(name = "quantidade_repeticoes", nullable = false)
    private int repeticoes;

    @Column(name = "carga_sugerida_kg", precision = 5, scale = 2)
    private BigDecimal cargaSugeridaKg;

    @Column(name = "observacoes_adicionais")
    private String observacoesAdicionais;

    @Column(name = "ordem_no_treino")
    private int ordemNoTreino;
}