package com.academia.repository;

import com.academia.entity.instructor.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstructorRepository extends JpaRepository<Instructor, UUID> {}