package com.academia.service;

import com.academia.entity.ExecucaoExercicio.ExecucaoExercicio;
import com.academia.entity.ExecucaoExercicio.ExecucaoExercicioRequestDTO;
import com.academia.entity.ExecucaoExercicio.ExecucaoExercicioResponseDTO;
import com.academia.entity.Exercicio.Exercicio;
import com.academia.entity.Exercicio.ExercicioResponseDTO;
import com.academia.entity.cliente.Cliente;
import com.academia.entity.cliente.ClienteReferenceDTO;
import com.academia.entity.exercicioTreino.ExercicioTreino;
import com.academia.entity.treino.TreinoReferenceDTO;
import com.academia.exception.ResourceNotFoundException;
import com.academia.repository.ClienteRepository;
import com.academia.repository.ExercicioTreinoRepository;
import com.academia.repository.ExecucaoExercicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExecucaoExercicioService {

    private final ExecucaoExercicioRepository execucaoExercicioRepository;
    private final ClienteRepository clienteRepository;
    private final ExercicioTreinoRepository exercicioTreinoRepository;

    public ExecucaoExercicioService(ExecucaoExercicioRepository execucaoExercicioRepository,
                                    ClienteRepository clienteRepository,
                                    ExercicioTreinoRepository exercicioTreinoRepository) {
        this.execucaoExercicioRepository = execucaoExercicioRepository;
        this.clienteRepository = clienteRepository;
        this.exercicioTreinoRepository = exercicioTreinoRepository;
    }

    private ExecucaoExercicioResponseDTO convertToResponseDTO(ExecucaoExercicio execucao) {
        ClienteReferenceDTO clienteDTO = null;
        if (execucao.getCliente() != null) {
            clienteDTO = new ClienteReferenceDTO(
                    execucao.getCliente().getId(),
                    execucao.getCliente().getNome()
            );
        }

        TreinoReferenceDTO treinoDTO = null;
        ExercicioResponseDTO exercicioDTO = null;

        if (execucao.getExercicioTreino() != null) {
            ExercicioTreino et = execucao.getExercicioTreino();

            // Converte Treino para TreinoReferenceDTO
            if (et.getTreino() != null) {
                treinoDTO = new TreinoReferenceDTO(
                        et.getTreino().getId(),
                        et.getTreino().getNome(),
                        et.getTreino().getDiasDaSemana()
                );
            }

            // Converte Exercicio para ExercicioResponseDTO
            if (et.getExercicio() != null) {
                Exercicio exercicio = et.getExercicio();
                exercicioDTO = new ExercicioResponseDTO(
                        exercicio.getId(),
                        exercicio.getNome(),
                        exercicio.getDescricao(),
                        exercicio.getTipoEquipamento(),
                        exercicio.getGrupoMuscularPrincipal()
                );
            }
        }

        return new ExecucaoExercicioResponseDTO(
                execucao.getId(),
                clienteDTO,
                treinoDTO,
                exercicioDTO,
                execucao.getDataExecucao(),
                execucao.getSeriesRealizadas(),
                execucao.getRepeticoesRealizadas(),
                execucao.getCargaRealizadaKg(),
                execucao.getObservacoes()
        );
    }

    @Transactional
    public ExecucaoExercicioResponseDTO registrarExecucao(ExecucaoExercicioRequestDTO requestDTO) {
        Cliente cliente = clienteRepository.findById(requestDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + requestDTO.getClienteId()));

        ExercicioTreino exercicioTreino = exercicioTreinoRepository.findById(requestDTO.getExercicioTreinoId())
                .orElseThrow(() -> new ResourceNotFoundException("Exercício do Treino não encontrado com ID: " + requestDTO.getExercicioTreinoId()));

        ExecucaoExercicio execucao = new ExecucaoExercicio();
        execucao.setCliente(cliente);
        execucao.setExercicioTreino(exercicioTreino);
        execucao.setDataExecucao(requestDTO.getDataExecucao() != null ? requestDTO.getDataExecucao() : LocalDateTime.now());
        execucao.setSeriesRealizadas(requestDTO.getSeriesRealizadas());
        execucao.setRepeticoesRealizadas(requestDTO.getRepeticoesRealizadas());
        execucao.setCargaRealizadaKg(requestDTO.getCargaRealizadaKg());
        execucao.setObservacoes(requestDTO.getObservacoes());

        execucao = execucaoExercicioRepository.save(execucao);
        return convertToResponseDTO(execucao);
    }

    @Transactional(readOnly = true)
    public ExecucaoExercicioResponseDTO buscarExecucaoPorId(UUID id) {
        ExecucaoExercicio execucao = execucaoExercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Execução de Exercício não encontrada com ID: " + id));
        // Força o carregamento das entidades relacionadas se forem LAZY
        if (execucao.getCliente() != null) execucao.getCliente().getId();
        if (execucao.getExercicioTreino() != null) {
            execucao.getExercicioTreino().getId();
            if (execucao.getExercicioTreino().getTreino() != null) execucao.getExercicioTreino().getTreino().getId();
            if (execucao.getExercicioTreino().getExercicio() != null) execucao.getExercicioTreino().getExercicio().getId();
        }
        return convertToResponseDTO(execucao);
    }

    @Transactional(readOnly = true)
    public List<ExecucaoExercicioResponseDTO> listarTodasExecucoes() {
        List<ExecucaoExercicio> execucoes = execucaoExercicioRepository.findAll();
        // Força o carregamento das coleções e entidades para evitar LazyInitializationException
        execucoes.forEach(execucao -> {
            if (execucao.getCliente() != null) execucao.getCliente().getId();
            if (execucao.getExercicioTreino() != null) {
                execucao.getExercicioTreino().getId();
                if (execucao.getExercicioTreino().getTreino() != null) execucao.getExercicioTreino().getTreino().getId();
                if (execucao.getExercicioTreino().getExercicio() != null) execucao.getExercicioTreino().getExercicio().getId();
            }
        });
        return execucoes.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ExecucaoExercicioResponseDTO> buscarExecucoesPorCliente(UUID clienteId) {
        List<ExecucaoExercicio> execucoes = execucaoExercicioRepository.findByClienteId(clienteId);
        execucoes.forEach(execucao -> {
            if (execucao.getCliente() != null) execucao.getCliente().getId();
            if (execucao.getExercicioTreino() != null) {
                execucao.getExercicioTreino().getId();
                if (execucao.getExercicioTreino().getTreino() != null) execucao.getExercicioTreino().getTreino().getId();
                if (execucao.getExercicioTreino().getExercicio() != null) execucao.getExercicioTreino().getExercicio().getId();
            }
        });
        return execucoes.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ExecucaoExercicioResponseDTO atualizarExecucao(UUID id, ExecucaoExercicioRequestDTO requestDTO) {
        ExecucaoExercicio execucaoExistente = execucaoExercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Execução de Exercício não encontrada com ID: " + id));

        // Validações de existência de Cliente e ExercicioTreino (se forem alterados)
        if (!execucaoExistente.getCliente().getId().equals(requestDTO.getClienteId())) {
            Cliente novoCliente = clienteRepository.findById(requestDTO.getClienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Novo Cliente não encontrado com ID: " + requestDTO.getClienteId()));
            execucaoExistente.setCliente(novoCliente);
        }
        if (!execucaoExistente.getExercicioTreino().getId().equals(requestDTO.getExercicioTreinoId())) {
            ExercicioTreino novoExercicioTreino = exercicioTreinoRepository.findById(requestDTO.getExercicioTreinoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Novo Exercício do Treino não encontrado com ID: " + requestDTO.getExercicioTreinoId()));
            execucaoExistente.setExercicioTreino(novoExercicioTreino);
        }

        execucaoExistente.setDataExecucao(requestDTO.getDataExecucao() != null ? requestDTO.getDataExecucao() : LocalDateTime.now());
        execucaoExistente.setSeriesRealizadas(requestDTO.getSeriesRealizadas());
        execucaoExistente.setRepeticoesRealizadas(requestDTO.getRepeticoesRealizadas());
        execucaoExistente.setCargaRealizadaKg(requestDTO.getCargaRealizadaKg());
        execucaoExistente.setObservacoes(requestDTO.getObservacoes());

        execucaoExistente = execucaoExercicioRepository.save(execucaoExistente);
        return convertToResponseDTO(execucaoExistente);
    }

    @Transactional
    public void deletarExecucao(UUID id) {
        if (!execucaoExercicioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Execução de Exercício não encontrada com ID: " + id);
        }
        execucaoExercicioRepository.deleteById(id);
    }
}