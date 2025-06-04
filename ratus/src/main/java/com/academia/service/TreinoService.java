package com.academia.service;

import com.academia.entity.Treino;
import com.academia.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    public List<Treino> findAll() {
        return treinoRepository.findAll();
    }

    public Optional<Treino> findById(Long id) {
        return treinoRepository.findById(id);
    }

    public Treino save(Treino treino) {
        // Adicionar validações ou lógica de negócio antes de salvar, se necessário
        // Por exemplo, verificar se o cliente existe
        return treinoRepository.save(treino);
    }

    public void deleteById(Long id) {
        // Adicionar validações ou lógica de negócio antes de deletar, se necessário
        treinoRepository.deleteById(id);
    }

    // Outros métodos de serviço relacionados a Treino podem ser adicionados aqui
    // Exemplo: buscarTreinosPorCliente, adicionarExercicioAoTreino, etc.
}

