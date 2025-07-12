package com.academia.repository; // Pacote para Repositórios

import com.academia.entity.Employee.Employee; // Importa a entidade base Employee
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    // Método para buscar um funcionário pelo CPF. Útil para validação de unicidade.
    Optional<Employee> findByCpf(String cpf);

    // Método para buscar um funcionário pelo E-mail. Útil para validação de unicidade.
    Optional<Employee> findByEmail(String email);

    // Você pode adicionar outros métodos de busca específicos para funcionários aqui.
}
