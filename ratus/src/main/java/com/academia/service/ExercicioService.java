package com.academia.service;

import com.academia.entity.Exercicio;
import com.academia.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<Exercicio> findAll() {
        return exercicioRepository.findAll();
    }

    public Optional<Exercicio> findById(Long id) {
        return exercicioRepository.findById(id);
    }

    public Exercicio save(Exercicio exercicio) {
        // Adicionar validações ou lógica de negócio antes de salvar, se necessário
        return exercicioRepository.save(exercicio);
    }

    public void deleteById(Long id) {
        // Adicionar validações ou lógica de negócio antes de deletar, se necessário
        exercicioRepository.deleteById(id);
    }

    // Outros métodos de serviço relacionados a Exercicio podem ser adicionados aqui
}

