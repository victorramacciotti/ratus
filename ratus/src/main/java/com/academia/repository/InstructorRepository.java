package com.academia.repository;

import com.academia.entity.Employee.instructor.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InstructorRepository extends JpaRepository<Instructor, UUID> {
    Optional<Object> findByEmail(String email);

    Optional<Object> findByCpf(String cpf);
}