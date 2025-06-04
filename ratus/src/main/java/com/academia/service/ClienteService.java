package com.academia.service;

import com.academia.entity.Cliente;
import com.academia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        // Adicionar validações ou lógica de negócio antes de salvar, se necessário
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        // Adicionar validações ou lógica de negócio antes de deletar, se necessário
        clienteRepository.deleteById(id);
    }

    // Outros métodos de serviço relacionados a Cliente podem ser adicionados aqui
    // Exemplo: atualizarCliente, buscarPorEmail, etc.
}

