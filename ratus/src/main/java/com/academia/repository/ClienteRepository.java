package com.academia.repository;

import com.academia.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
    // Exemplo: Cliente findByEmail(String email);
}

