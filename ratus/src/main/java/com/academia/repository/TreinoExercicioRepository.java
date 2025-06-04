package com.academia.repository;

import com.academia.entity.TreinoExercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinoExercicioRepository extends JpaRepository<TreinoExercicio, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
    // Exemplo: List<TreinoExercicio> findByTreinoId(Long treinoId);
}

