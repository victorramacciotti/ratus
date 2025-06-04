package com.academia.repository;

import com.academia.entity.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
}

