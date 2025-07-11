package com.academia.entity;

import com.academia.entity.instructor.Instructor;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "treino")
public class Treino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "instrutor_id")
    private Instructor instrutor;
}

