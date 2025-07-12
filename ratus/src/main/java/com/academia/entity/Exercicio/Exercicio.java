package com.academia.entity.Exercicio;

import com.academia.enums.GrupoMuscular;
import com.academia.enums.TipoEquipamento; // Importar o novo enum
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "exercicio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_exercicio")
    private UUID id;

    @Column(name = "nome_exercicio", nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao_exercicio", length = 500)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_equipamento")
    private TipoEquipamento tipoEquipamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "grupo_muscular_principal")
    private GrupoMuscular grupoMuscularPrincipal;
}