package com.academia.service;

import com.academia.entity.TreinoExercicio;
import com.academia.repository.TreinoExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TreinoExercicioService {

    @Autowired
    private TreinoExercicioRepository treinoExercicioRepository;

    public List<TreinoExercicio> findAll() {
        return treinoExercicioRepository.findAll();
    }

    public Optional<TreinoExercicio> findById(Long id) {
        return treinoExercicioRepository.findById(id);
    }

    public TreinoExercicio save(TreinoExercicio treinoExercicio) {
        // Adicionar validações ou lógica de negócio antes de salvar, se necessário
        // Por exemplo, verificar se o treino e o exercício existem
        return treinoExercicioRepository.save(treinoExercicio);
    }

    public void deleteById(Long id) {
        // Adicionar validações ou lógica de negócio antes de deletar, se necessário
        treinoExercicioRepository.deleteById(id);
    }

    // Outros métodos de serviço relacionados a TreinoExercicio podem ser adicionados aqui
    // Exemplo: buscarExerciciosPorTreino, etc.
}

