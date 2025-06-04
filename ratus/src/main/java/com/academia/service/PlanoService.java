package com.academia.service;

import com.academia.entity.Plano;
import com.academia.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    public List<Plano> findAll() {
        return planoRepository.findAll();
    }

    public Optional<Plano> findById(Long id) {
        return planoRepository.findById(id);
    }

    public Plano save(Plano plano) {
        // Adicionar validações ou lógica de negócio antes de salvar, se necessário
        return planoRepository.save(plano);
    }

    public void deleteById(Long id) {
        // Adicionar validações ou lógica de negócio antes de deletar, se necessário
        planoRepository.deleteById(id);
    }

    // Outros métodos de serviço relacionados a Plano podem ser adicionados aqui
}

