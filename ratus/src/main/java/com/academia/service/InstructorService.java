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
    private InstructorRepository instructorRepository;

    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> findById(UUID id) {
        return this.instructorRepository.findById(id);
    }

    public Instructor createInstrutor(InstructorRequestDTO data){
        if (instructorRepository.findByCpf(data.cpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um instrutor com este CPF.");
        }
        if (instructorRepository.findByEmail(data.email()).isPresent()) {
            throw new IllegalArgumentException("Já existe um instrutor com este email.");
        }

        Instructor newInstructor = new Instructor();
        newInstructor.setCpf(data.cpf());
        newInstructor.setNome(data.nome());
        newInstructor.setEmail(data.email());
        newInstructor.setTelefone(data.telefone());
        newInstructor.setEscalaTrabalho(data.escalaTrabalho());
        newInstructor.setSalario(data.salario());
        newInstructor.setEspecialidade(data.especialidade());

        return instructorRepository.save(newInstructor);
    }

    public Optional<Instructor> updateInstructor(UUID id, InstructorRequestDTO newData) {
        return instructorRepository.findById(id)
                .map(existingInstructor -> {
                    if (newData.cpf() != null && !newData.cpf().isEmpty()) {
                        if (!newData.cpf().equals(existingInstructor.getCpf())) {
                            if (instructorRepository.findByCpf(newData.cpf()).isPresent()) {
                                throw new IllegalArgumentException("Novo CPF já cadastrado para outro instrutor.");
                            }
                        }
                        existingInstructor.setCpf(newData.cpf());
                    }

                    if (newData.email() != null && !newData.email().isEmpty()) {
                        if (!newData.email().equals(existingInstructor.getEmail())) {
                            if (instructorRepository.findByEmail(newData.email()).isPresent()) {
                                throw new IllegalArgumentException("Novo email já cadastrado para outro instrutor.");
                            }
                        }
                        existingInstructor.setEmail(newData.email());
                    }

                    if (newData.nome() != null && !newData.nome().isEmpty()) {
                        existingInstructor.setNome(newData.nome());
                    }

                    if (newData.telefone() != null) {
                        existingInstructor.setTelefone(newData.telefone());
                    }

                    if (newData.escalaTrabalho() != null && (newData.escalaTrabalho()).describeConstable().isEmpty()) {
                        existingInstructor.setEscalaTrabalho(newData.escalaTrabalho());
                    }

                    if (newData.salario() != null) {
                        existingInstructor.setSalario(newData.salario());
                    }

                    if (newData.especialidade() != null && !newData.especialidade().isBlank()) {
                        existingInstructor.setEspecialidade(newData.especialidade());
                    }

                    return instructorRepository.save(existingInstructor);
                });
    }

    public boolean deleteInstructor(UUID id) {
        if (instructorRepository.existsById(id)) {
            instructorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
