package com.academia.service;

import com.academia.entity.Employee.instructor.InstructorRequestDTO;
import com.academia.entity.Employee.instructor.Instructor;
import com.academia.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository repository;

    public List<Instructor> findAllInstructors() {
        return repository.findAll();
    }

    public Optional<Instructor> findById(UUID id) {
        return this.repository.findById(id);
    }

    public Instructor createInstrutor(InstructorRequestDTO data){
        Instructor newInstructor = new Instructor();
        newInstructor.setId(data.id());
        newInstructor.setCpf(data.cpf());
        newInstructor.setNome(data.nome());
        newInstructor.setEmail(data.email());
        newInstructor.setTelefone(data.telefone());
        newInstructor.setEscalaTrabalho(data.escalaTrabalho());
        newInstructor.setSalario(data.salario());
        newInstructor.setEspecialidade(data.especialidade());

        repository.save(newInstructor);

        return newInstructor;
    }

    public boolean deleteInstructor(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
