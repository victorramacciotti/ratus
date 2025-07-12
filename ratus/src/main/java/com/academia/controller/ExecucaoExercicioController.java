package com.academia.controller;

import com.academia.entity.ExecucaoExercicio.ExecucaoExercicioRequestDTO;
import com.academia.entity.ExecucaoExercicio.ExecucaoExercicioResponseDTO;
import com.academia.service.ExecucaoExercicioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/execucoes-exercicios") // Endpoint base para execuções de exercícios
public class ExecucaoExercicioController {

    private final ExecucaoExercicioService execucaoExercicioService;

    public ExecucaoExercicioController(ExecucaoExercicioService execucaoExercicioService) {
        this.execucaoExercicioService = execucaoExercicioService;
    }

    /**
     * Registra uma nova execução de exercício.
     * Apenas usuários com as roles ADMIN ou INSTRUTOR podem realizar esta operação.
     * @param requestDTO Dados da execução do exercício a ser registrada.
     * @return ResponseEntity com o ExecucaoExercicioResponseDTO da execução registrada e status 201 Created.
     */
    @PostMapping
    public ResponseEntity<ExecucaoExercicioResponseDTO> registrarExecucao(@RequestBody @Valid ExecucaoExercicioRequestDTO requestDTO) {
        ExecucaoExercicioResponseDTO novaExecucao = execucaoExercicioService.registrarExecucao(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaExecucao);
    }

    /**
     * Busca uma execução de exercício pelo seu ID único.
     * Apenas usuários com as roles ADMIN, INSTRUTOR ou RECEPCIONISTA podem realizar esta operação.
     * @param id ID da execução do exercício a ser buscada.
     * @return ResponseEntity com o ExecucaoExercicioResponseDTO da execução encontrada e status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExecucaoExercicioResponseDTO> buscarExecucaoPorId(@PathVariable UUID id) {
        ExecucaoExercicioResponseDTO execucao = execucaoExercicioService.buscarExecucaoPorId(id);
        return ResponseEntity.ok(execucao);
    }

    /**
     * Lista todas as execuções de exercícios registradas.
     * Apenas usuários com as roles ADMIN, INSTRUTOR ou RECEPCIONISTA podem realizar esta operação.
     * @return ResponseEntity com uma lista de ExecucaoExercicioResponseDTOs e status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<ExecucaoExercicioResponseDTO>> listarTodasExecucoes() {
        List<ExecucaoExercicioResponseDTO> execucoes = execucaoExercicioService.listarTodasExecucoes();
        return ResponseEntity.ok(execucoes);
    }

    /**
     * Busca execuções de exercícios por ID do cliente.
     * Apenas usuários com as roles ADMIN, INSTRUTOR ou RECEPCIONISTA podem realizar esta operação.
     * @param clienteId O ID do cliente.
     * @return ResponseEntity com uma lista de ExecucaoExercicioResponseDTOs para o cliente especificado e status 200 OK.
     */
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ExecucaoExercicioResponseDTO>> buscarExecucoesPorCliente(@PathVariable UUID clienteId) {
        List<ExecucaoExercicioResponseDTO> execucoes = execucaoExercicioService.buscarExecucoesPorCliente(clienteId);
        return ResponseEntity.ok(execucoes);
    }

    /**
     * Atualiza os dados de uma execução de exercício existente.
     * Apenas usuários com as roles ADMIN ou INSTRUTOR podem realizar esta operação.
     * @param id ID da execução do exercício a ser atualizada.
     * @param requestDTO Novos dados da execução.
     * @return ResponseEntity com o ExecucaoExercicioResponseDTO da execução atualizada e status 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExecucaoExercicioResponseDTO> atualizarExecucao(@PathVariable UUID id, @RequestBody @Valid ExecucaoExercicioRequestDTO requestDTO) {
        ExecucaoExercicioResponseDTO execucaoAtualizada = execucaoExercicioService.atualizarExecucao(id, requestDTO);
        return ResponseEntity.ok(execucaoAtualizada);
    }

    /**
     * Deleta uma execução de exercício do sistema.
     * Apenas usuários com a role ADMIN ou INSTRUTOR podem realizar esta operação.
     * @param id ID da execução do exercício a ser deletada.
     * @return ResponseEntity com status 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExecucao(@PathVariable UUID id) {
        execucaoExercicioService.deletarExecucao(id);
        return ResponseEntity.noContent().build();
    }
}