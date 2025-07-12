package com.academia.entity.cliente;

import com.academia.entity.treino.Treino;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cliente")
    private UUID id;

    @Column(name = "cpf_cliente", nullable = false, unique = true)
    private String cpf;

    @Column(name = "nome_cliente", nullable = false)
    private String nome;

    @Column(name = "email_cliente", unique = true)
    private String email;

    @Column(name = "telefone_cliente", nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Treino> treinos;
}