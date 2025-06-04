package com.academia.repository;

import com.academia.entity.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
}

