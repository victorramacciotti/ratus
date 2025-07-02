package com.academia.repository;

import org.springframework.stereotype.Repository;

import com.academia.entity.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Optional<Cliente> findByCpf(String cpf);
    boolean existsByEmail(String email); 
    boolean existsByCpf(String cpf);
}