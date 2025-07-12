package com.academia.entity;

import com.academia.entity.Employee.instructor.Instructor;
import com.academia.enums.DiasDaSemana;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "treino", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_cliente", "dia_da_semana"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Treino {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_treino")
    private UUID id;

    @Column(name = "nome_treino", nullable = false)
    private String nome;

    @Lob
    @Column(name = "descricao_treino", columnDefinition = "MEDIUMTEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_da_semana", nullable = false)
    private DiasDaSemana diasDaSemana;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instrutor")
    private Instructor instrutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ExercicioTreino> exerciciosDoTreino;
}