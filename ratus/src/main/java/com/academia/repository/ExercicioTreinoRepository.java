package com.academia.repository;

import com.academia.entity.exercicioTreino.ExercicioTreino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExercicioTreinoRepository extends JpaRepository<ExercicioTreino, UUID> {
}