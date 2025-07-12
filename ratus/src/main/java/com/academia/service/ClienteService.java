package com.academia.service;

import com.academia.entity.Employee.instructor.InstructorResponseDTO;
import com.academia.entity.Exercicio.ExercicioResponseDTO;
import com.academia.entity.cliente.Cliente;
import com.academia.entity.cliente.ClienteReferenceDTO;
import com.academia.entity.cliente.ClienteRequestDTO;
import com.academia.entity.cliente.ClienteResponseDTO;
import com.academia.entity.exercicioTreino.ExercicioTreinoResponseDTO;
import com.academia.entity.treino.TreinoResponseDTO;
import com.academia.exception.ResourceNotFoundException;
import com.academia.exception.ValidationException;
import com.academia.repository.ClienteRepository;
import com.academia.repository.ExercicioRepository;
import com.academia.repository.InstructorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    // Adicionei repositórios necessários para a conversão de Treino para TreinoResponseDTO
    private final InstructorRepository instructorRepository;
    private final ExercicioRepository exercicioRepository;

    public ClienteService(ClienteRepository clienteRepository,
                          InstructorRepository instructorRepository,
                          ExercicioRepository exercicioRepository) {
        this.clienteRepository = clienteRepository;
        this.instructorRepository = instructorRepository;
        this.exercicioRepository = exercicioRepository;
    }

    private ClienteResponseDTO convertToResponseDTO(Cliente cliente) {
        List<TreinoResponseDTO> treinosDTO = new ArrayList<>();
        if (cliente.getTreinos() != null && !cliente.getTreinos().isEmpty()) {
            treinosDTO = cliente.getTreinos().stream()
                    .map(treino -> {
                        // Converte Instrutor para InstructorResponseDTO (seu DTO padrão)
                        InstructorResponseDTO instructorDTO = null;
                        if (treino.getInstrutor() != null) {
                            instructorDTO = InstructorResponseDTO.fromEntity(treino.getInstrutor());
                        }

                        // Converte Cliente para ClienteReferenceDTO (para evitar loop)
                        ClienteReferenceDTO clienteRefDTO = null;
                        if (treino.getCliente() != null) {
                            clienteRefDTO = new ClienteReferenceDTO(
                                    treino.getCliente().getId(),
                                    treino.getCliente().getNome()
                                    // Se o ClienteReferenceDTO tiver CPF, adicione aqui:
                                    // , treino.getCliente().getCpf()
                            );
                        }

                        // Converte a lista de ExercicioTreino para ExercicioTreinoResponseDTO
                        List<ExercicioTreinoResponseDTO> exerciciosDoTreinoDTO = new ArrayList<>();
                        if (treino.getExerciciosDoTreino() != null && !treino.getExerciciosDoTreino().isEmpty()) {
                            exerciciosDoTreinoDTO = treino.getExerciciosDoTreino().stream()
                                    .map(et -> {
                                        ExercicioResponseDTO exercicioRespDTO = new ExercicioResponseDTO(
                                                et.getExercicio().getId(),
                                                et.getExercicio().getNome(),
                                                et.getExercicio().getDescricao(),
                                                et.getExercicio().getTipoEquipamento(),
                                                et.getExercicio().getGrupoMuscularPrincipal()
                                        );
                                        return new ExercicioTreinoResponseDTO(
                                                et.getId(),
                                                exercicioRespDTO,
                                                et.getSeries(),
                                                et.getRepeticoes(),
                                                et.getCargaSugeridaKg(),
                                                et.getObservacoesAdicionais(),
                                                et.getOrdemNoTreino()
                                        );
                                    })
                                    .collect(Collectors.toList());
                        }

                        // Constrói o TreinoResponseDTO com todos os argumentos
                        return new TreinoResponseDTO(
                                treino.getId(),
                                treino.getNome(),
                                treino.getDescricao(),
                                treino.getDiasDaSemana(),
                                instructorDTO,
                                clienteRefDTO, // Usa o ClienteReferenceDTO aqui
                                exerciciosDoTreinoDTO
                        );
                    })
                    .collect(Collectors.toList());
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
        // Força o carregamento da coleção de treinos se for LAZY
        // Isso é importante para evitar LazyInitializationException ao converter para DTO fora da transação.
        if (cliente.getTreinos() != null) {
            cliente.getTreinos().size();
            cliente.getTreinos().forEach(treino -> {
                if (treino.getExerciciosDoTreino() != null) {
                    treino.getExerciciosDoTreino().size(); // Força carregamento dos exerciciosDoTreino
                    treino.getExerciciosDoTreino().forEach(et -> {
                        if (et.getExercicio() != null) {
                            et.getExercicio().getId(); // Força carregamento do Exercício
                        }
                    });
                }
            });
        }
        return convertToResponseDTO(cliente);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listarTodosClientes() {
        // Ao listar todos, carregar os treinos e exercícios aninhados pode ser custoso.
        // Considere otimizar com @EntityGraph ou DTOs ainda mais simplificados se a lista for muito grande.
        List<Cliente> clientes = clienteRepository.findAll();
        // Força o carregamento das coleções para evitar LazyInitializationException
        clientes.forEach(cliente -> {
            if (cliente.getTreinos() != null) {
                cliente.getTreinos().size();
                cliente.getTreinos().forEach(treino -> {
                    if (treino.getInstrutor() != null) {
                        treino.getInstrutor().getId(); // Força carregamento do Instrutor
                    }
                    if (treino.getExerciciosDoTreino() != null) {
                        treino.getExerciciosDoTreino().size();
                        treino.getExerciciosDoTreino().forEach(et -> {
                            if (et.getExercicio() != null) {
                                et.getExercicio().getId(); // Força carregamento do Exercício
                            }
                        });
                    }
                });
            }
        });
        return clientes.stream()
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