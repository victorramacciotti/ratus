package com.academia.service;

import com.academia.entity.Employee.instructor.Instructor;
import com.academia.entity.Employee.instructor.InstructorResponseDTO;
import com.academia.entity.Exercicio.Exercicio;
import com.academia.entity.Exercicio.ExercicioResponseDTO;
import com.academia.entity.cliente.Cliente;
import com.academia.entity.cliente.ClienteReferenceDTO;
import com.academia.entity.exercicioTreino.ExercicioTreino;
import com.academia.entity.exercicioTreino.ExercicioTreinoRequestDTO;
import com.academia.entity.exercicioTreino.ExercicioTreinoResponseDTO;
import com.academia.entity.treino.Treino;
import com.academia.entity.treino.TreinoRequestDTO;
import com.academia.entity.treino.TreinoResponseDTO;
import com.academia.exception.ResourceNotFoundException;
import com.academia.exception.ValidationException;
import com.academia.repository.ClienteRepository;
import com.academia.repository.ExercicioRepository;
import com.academia.repository.InstructorRepository;
import com.academia.repository.TreinoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;
    private final ClienteRepository clienteRepository;
    private final InstructorRepository instructorRepository;
    private final ExercicioRepository exercicioRepository;

    public TreinoService(TreinoRepository treinoRepository,
                         ClienteRepository clienteRepository,
                         InstructorRepository instructorRepository,
                         ExercicioRepository exercicioRepository) {
        this.treinoRepository = treinoRepository;
        this.clienteRepository = clienteRepository;
        this.instructorRepository = instructorRepository;
        this.exercicioRepository = exercicioRepository;
    }

    private TreinoResponseDTO convertToResponseDTO(Treino treino) {
        InstructorResponseDTO instructorDTO = null;
        if (treino.getInstrutor() != null) {
            // Utiliza o método fromEntity do seu InstructorResponseDTO para conversão
            instructorDTO = InstructorResponseDTO.fromEntity(treino.getInstrutor());
        }

        ClienteReferenceDTO clienteDTO = null;
        if (treino.getCliente() != null) {
            // Utiliza ClienteReferenceDTO para evitar StackOverflowError em relacionamentos bidirecionais
            clienteDTO = new ClienteReferenceDTO(
                    treino.getCliente().getId(),
                    treino.getCliente().getNome()
                    // Se você quiser o CPF no ClienteReferenceDTO, adicione-o no construtor
                    // , treino.getCliente().getCpf()
            );
        }

        List<ExercicioTreinoResponseDTO> exerciciosDoTreinoDTO = new ArrayList<>();
        if (treino.getExerciciosDoTreino() != null && !treino.getExerciciosDoTreino().isEmpty()) {
            exerciciosDoTreinoDTO = treino.getExerciciosDoTreino().stream()
                    .map(et -> {
                        // Converte a entidade Exercicio para ExercicioResponseDTO
                        ExercicioResponseDTO exercicioRespDTO = new ExercicioResponseDTO(
                                et.getExercicio().getId(),
                                et.getExercicio().getNome(),
                                et.getExercicio().getDescricao(),
                                et.getExercicio().getTipoEquipamento(),
                                et.getExercicio().getGrupoMuscularPrincipal()
                        );
                        // Cria o ExercicioTreinoResponseDTO
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

        return new TreinoResponseDTO(
                treino.getId(),
                treino.getNome(),
                treino.getDescricao(),
                treino.getDiasDaSemana(),
                instructorDTO,
                clienteDTO,
                exerciciosDoTreinoDTO
        );
    }

    @Transactional
    public TreinoResponseDTO criarTreino(TreinoRequestDTO requestDTO) {
        // Validações de existência de Cliente e Instrutor
        Cliente cliente = clienteRepository.findById(requestDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + requestDTO.getClienteId()));
        Instructor instrutor = instructorRepository.findById(requestDTO.getInstrutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instrutor não encontrado com ID: " + requestDTO.getInstrutorId()));

        // Validação de unicidade: um cliente só pode ter um treino por dia da semana
        if (treinoRepository.findByClienteIdAndDiasDaSemana(requestDTO.getClienteId(), requestDTO.getDiasDaSemana()).isPresent()) {
            throw new ValidationException("Já existe um treino para este cliente neste dia da semana.");
        }

        Treino treino = new Treino();
        treino.setNome(requestDTO.getNome());
        treino.setDescricao(requestDTO.getDescricao());
        treino.setDiasDaSemana(requestDTO.getDiasDaSemana());
        treino.setCliente(cliente);
        treino.setInstrutor(instrutor);

        // Processa os exercícios do treino
        List<ExercicioTreino> exerciciosDoTreino = new ArrayList<>();
        for (ExercicioTreinoRequestDTO etDTO : requestDTO.getExerciciosDoTreino()) {
            Exercicio exercicio = exercicioRepository.findById(etDTO.getExercicioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Exercício não encontrado com ID: " + etDTO.getExercicioId()));

            ExercicioTreino exercicioTreino = new ExercicioTreino();
            exercicioTreino.setTreino(treino); // Define a referência ao treino pai
            exercicioTreino.setExercicio(exercicio);
            exercicioTreino.setSeries(etDTO.getSeries());
            exercicioTreino.setRepeticoes(etDTO.getRepeticoes());
            exercicioTreino.setCargaSugeridaKg(etDTO.getCargaSugeridaKg());
            exercicioTreino.setObservacoesAdicionais(etDTO.getObservacoesAdicionais());
            exercicioTreino.setOrdemNoTreino(etDTO.getOrdemNoTreino());
            exerciciosDoTreino.add(exercicioTreino);
        }
        treino.setExerciciosDoTreino(exerciciosDoTreino);

        treino = treinoRepository.save(treino);
        return convertToResponseDTO(treino);
    }

    @Transactional(readOnly = true)
    public TreinoResponseDTO buscarTreinoPorId(UUID id) {
        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treino não encontrado com ID: " + id));
        // Força o carregamento da coleção de exerciciosDoTreino se for LAZY
        // Isso é importante para evitar LazyInitializationException ao converter para DTO fora da transação.
        treino.getExerciciosDoTreino().size();
        return convertToResponseDTO(treino);
    }

    @Transactional(readOnly = true)
    public List<TreinoResponseDTO> listarTodosTreinos() {
        return treinoRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TreinoResponseDTO atualizarTreino(UUID id, TreinoRequestDTO requestDTO) {
        Treino treinoExistente = treinoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treino não encontrado com ID: " + id));

        // Validações de existência de Cliente e Instrutor
        Cliente cliente = clienteRepository.findById(requestDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + requestDTO.getClienteId()));
        Instructor instrutor = instructorRepository.findById(requestDTO.getInstrutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instrutor não encontrado com ID: " + requestDTO.getInstrutorId()));

        // Validação de unicidade ao atualizar (ignora o próprio treino)
        Optional<Treino> treinoComMesmoDiaParaCliente = treinoRepository.findByClienteIdAndDiasDaSemana(requestDTO.getClienteId(), requestDTO.getDiasDaSemana());
        if (treinoComMesmoDiaParaCliente.isPresent() && !treinoComMesmoDiaParaCliente.get().getId().equals(id)) {
            throw new ValidationException("Já existe outro treino para este cliente neste dia da semana.");
        }

        treinoExistente.setNome(requestDTO.getNome());
        treinoExistente.setDescricao(requestDTO.getDescricao());
        treinoExistente.setDiasDaSemana(requestDTO.getDiasDaSemana());
        treinoExistente.setCliente(cliente);
        treinoExistente.setInstrutor(instrutor);

        // Atualiza a lista de exerciciosDoTreino
        // Limpa a lista existente e adiciona os novos (CascadeType.ALL e orphanRemoval=true cuidam da persistência)
        treinoExistente.getExerciciosDoTreino().clear();
        for (ExercicioTreinoRequestDTO etDTO : requestDTO.getExerciciosDoTreino()) {
            Exercicio exercicio = exercicioRepository.findById(etDTO.getExercicioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Exercício não encontrado com ID: " + etDTO.getExercicioId()));

            ExercicioTreino exercicioTreino = new ExercicioTreino();
            exercicioTreino.setTreino(treinoExistente); // Define a referência ao treino pai
            exercicioTreino.setExercicio(exercicio);
            exercicioTreino.setSeries(etDTO.getSeries());
            exercicioTreino.setRepeticoes(etDTO.getRepeticoes());
            exercicioTreino.setCargaSugeridaKg(etDTO.getCargaSugeridaKg());
            exercicioTreino.setObservacoesAdicionais(etDTO.getObservacoesAdicionais());
            exercicioTreino.setOrdemNoTreino(etDTO.getOrdemNoTreino());
            treinoExistente.getExerciciosDoTreino().add(exercicioTreino);
        }

        treinoExistente = treinoRepository.save(treinoExistente);
        return convertToResponseDTO(treinoExistente);
    }

    @Transactional
    public void deletarTreino(UUID id) {
        if (!treinoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Treino não encontrado com ID: " + id);
        }
        treinoRepository.deleteById(id);
    }
}