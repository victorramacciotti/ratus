package com.academia.repository; // Sugestão de pacote para Repositories

import com.academia.entity.Exercicio.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, UUID> {
    // Você pode adicionar métodos de busca personalizados aqui, se precisar
    Optional<Exercicio> findByNome(String nome);
}