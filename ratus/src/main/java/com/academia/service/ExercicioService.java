package com.academia.service; // Sugestão de pacote para Services

import com.academia.entity.Exercicio.Exercicio;
import com.academia.entity.Exercicio.ExercicioRequestDTO;
import com.academia.entity.Exercicio.ExercicioResponseDTO;
import com.academia.exception.ResourceNotFoundException;
import com.academia.exception.ValidationException;
import com.academia.repository.ExercicioRepository;
import org.springframework.beans.BeanUtils; // Utilitário para copiar propriedades
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    public ExercicioService(ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

    @Transactional
    public ExercicioResponseDTO criarExercicio(ExercicioRequestDTO requestDTO) {
        // Validação de nome único antes de salvar
        if (exercicioRepository.findByNome(requestDTO.getNome()).isPresent()) {
            throw new ValidationException("Já existe um exercício com este nome: " + requestDTO.getNome());
        }

        Exercicio exercicio = new Exercicio();
        BeanUtils.copyProperties(requestDTO, exercicio); // Copia propriedades do DTO para a entidade
        exercicio = exercicioRepository.save(exercicio);
        return new ExercicioResponseDTO(
                exercicio.getId(),
                exercicio.getNome(),
                exercicio.getDescricao(),
                exercicio.getTipoEquipamento(),
                exercicio.getGrupoMuscularPrincipal()
        );
    }

    @Transactional(readOnly = true)
    public ExercicioResponseDTO buscarExercicioPorId(UUID id) {
        Exercicio exercicio = exercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercício não encontrado com ID: " + id));
        return new ExercicioResponseDTO(
                exercicio.getId(),
                exercicio.getNome(),
                exercicio.getDescricao(),
                exercicio.getTipoEquipamento(),
                exercicio.getGrupoMuscularPrincipal()
        );
    }

    @Transactional(readOnly = true)
    public List<ExercicioResponseDTO> listarTodosExercicios() {
        return exercicioRepository.findAll().stream()
                .map(exercicio -> new ExercicioResponseDTO(
                        exercicio.getId(),
                        exercicio.getNome(),
                        exercicio.getDescricao(),
                        exercicio.getTipoEquipamento(),
                        exercicio.getGrupoMuscularPrincipal()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public ExercicioResponseDTO atualizarExercicio(UUID id, ExercicioRequestDTO requestDTO) {
        Exercicio exercicioExistente = exercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercício não encontrado com ID: " + id));

        // Verifica se o novo nome já existe e não pertence ao próprio exercício que está sendo atualizado
        Optional<Exercicio> exercicioComMesmoNome = exercicioRepository.findByNome(requestDTO.getNome());
        if (exercicioComMesmoNome.isPresent() && !exercicioComMesmoNome.get().getId().equals(id)) {
            throw new ValidationException("Já existe outro exercício com este nome: " + requestDTO.getNome());
        }

        BeanUtils.copyProperties(requestDTO, exercicioExistente, "id"); // Copia exceto o ID
        exercicioExistente = exercicioRepository.save(exercicioExistente);
        return new ExercicioResponseDTO(
                exercicioExistente.getId(),
                exercicioExistente.getNome(),
                exercicioExistente.getDescricao(),
                exercicioExistente.getTipoEquipamento(),
                exercicioExistente.getGrupoMuscularPrincipal()
        );
    }

    @Transactional
    public void deletarExercicio(UUID id) {
        if (!exercicioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exercício não encontrado com ID: " + id);
        }
        exercicioRepository.deleteById(id);
    }
}