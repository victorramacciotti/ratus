package com.academia.service;

import com.academia.entity.cliente.Cliente;
import com.academia.entity.cliente.ClienteRequestDTO;
import com.academia.entity.cliente.ClienteResponseDTO;
import com.academia.entity.treino.TreinoResponseDTO;
import com.academia.exception.ResourceNotFoundException;
import com.academia.exception.ValidationException;
import com.academia.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Método auxiliar para converter Cliente para ClienteResponseDTO
    private ClienteResponseDTO convertToResponseDTO(Cliente cliente) {
        List<TreinoResponseDTO> treinosDTO = null;
        // Verifica se a coleção de treinos foi inicializada e não está vazia
        if (cliente.getTreinos() != null && !cliente.getTreinos().isEmpty()) {
            treinosDTO = cliente.getTreinos().stream()
                    .map(treino -> new TreinoResponseDTO(
                            treino.getId(),
                            treino.getNome(),
                            treino.getDescricao(),
                            treino.getDiasDaSemana()
                    ))
                    .collect(Collectors.toList());
        } else {
            treinosDTO = List.of(); // Retorna uma lista vazia se não houver treinos
        }

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                treinosDTO
        );
    }

    @Transactional
    public ClienteResponseDTO criarCliente(ClienteRequestDTO requestDTO) {
        if (clienteRepository.findByCpf(requestDTO.getCpf()).isPresent()) {
            throw new ValidationException("Já existe um cliente com este CPF: " + requestDTO.getCpf());
        }
        if (requestDTO.getEmail() != null && !requestDTO.getEmail().isEmpty() &&
                clienteRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new ValidationException("Já existe um cliente com este e-mail: " + requestDTO.getEmail());
        }

        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(requestDTO, cliente);
        cliente = clienteRepository.save(cliente);
        return convertToResponseDTO(cliente);
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarClientePorId(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
        // Se a fetch strategy para treinos for LAZY, eles serão carregados aqui
        // dentro da transação.
        return convertToResponseDTO(cliente);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listarTodosClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClienteResponseDTO atualizarCliente(UUID id, ClienteRequestDTO requestDTO) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));

        Optional<Cliente> clienteComMesmoCpf = clienteRepository.findByCpf(requestDTO.getCpf());
        if (clienteComMesmoCpf.isPresent() && !clienteComMesmoCpf.get().getId().equals(id)) {
            throw new ValidationException("Já existe outro cliente com este CPF: " + requestDTO.getCpf());
        }

        if (requestDTO.getEmail() != null && !requestDTO.getEmail().isEmpty()) {
            Optional<Cliente> clienteComMesmoEmail = clienteRepository.findByEmail(requestDTO.getEmail());
            if (clienteComMesmoEmail.isPresent() && !clienteComMesmoEmail.get().getId().equals(id)) {
                throw new ValidationException("Já existe outro cliente com este e-mail: " + requestDTO.getEmail());
            }
        }

        BeanUtils.copyProperties(requestDTO, clienteExistente, "id");
        clienteExistente = clienteRepository.save(clienteExistente);
        return convertToResponseDTO(clienteExistente);
    }

    @Transactional
    public void deletarCliente(UUID id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}