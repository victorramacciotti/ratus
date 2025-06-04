package com.academia.repository;

import com.academia.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
    // Exemplo: List<Treino> findByClienteId(Long clienteId);
}

