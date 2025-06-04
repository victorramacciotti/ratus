package com.academia.repository;

import com.academia.entity.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
}

