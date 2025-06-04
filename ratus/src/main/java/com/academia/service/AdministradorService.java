package com.academia.service;

import com.academia.entity.Administrador;
import com.academia.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public List<Administrador> findAll() {
        return administradorRepository.findAll();
    }

    public Optional<Administrador> findById(Long id) {
        return administradorRepository.findById(id);
    }

    public Administrador save(Administrador administrador) {
        // Adicionar validações ou lógica de negócio antes de salvar, se necessário
        return administradorRepository.save(administrador);
    }

    public void deleteById(Long id) {
        // Adicionar validações ou lógica de negócio antes de deletar, se necessário
        administradorRepository.deleteById(id);
    }

    // Outros métodos de serviço relacionados a Administrador podem ser adicionados aqui
}

