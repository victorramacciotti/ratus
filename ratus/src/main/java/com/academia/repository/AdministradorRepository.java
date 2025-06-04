package com.academia.repository;

import com.academia.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
}

