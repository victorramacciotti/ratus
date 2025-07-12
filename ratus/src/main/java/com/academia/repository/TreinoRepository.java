package com.academia.repository;

import com.academia.entity.treino.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, UUID> {

    Optional<Treino> findByClienteIdAndDiasDaSemana(UUID clienteId, com.academia.enums.DiasDaSemana diasDaSemana);
}