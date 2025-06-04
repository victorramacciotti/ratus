package com.academia.service;

import com.academia.entity.Instrutor;
import com.academia.repository.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InstrutorService {

    @Autowired
    private InstrutorRepository instrutorRepository;

    public List<Instrutor> findAll() {
        return instrutorRepository.findAll();
    }

    public Optional<Instrutor> findById(Long id) {
        return instrutorRepository.findById(id);
    }

    public Instrutor save(Instrutor instrutor) {
        // Adicionar validações ou lógica de negócio antes de salvar, se necessário
        return instrutorRepository.save(instrutor);
    }

    public void deleteById(Long id) {
        // Adicionar validações ou lógica de negócio antes de deletar, se necessário
        instrutorRepository.deleteById(id);
    }

    // Outros métodos de serviço relacionados a Instrutor podem ser adicionados aqui
}

