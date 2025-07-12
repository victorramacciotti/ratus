package com.academia.controller;

import com.academia.entity.FolhaPagamento.FolhaPagamentoRequestDTO;
import com.academia.entity.FolhaPagamento.FolhaPagamentoResponseDTO;
import com.academia.service.FolhaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/folhas-pagamento") // Endpoint base para folhas de pagamento
public class FolhaPagamentoController {

    private final FolhaPagamentoService folhaPagamentoService;

    public FolhaPagamentoController(FolhaPagamentoService folhaPagamentoService) {
        this.folhaPagamentoService = folhaPagamentoService;
    }

    /**
     * Cria um novo registro de folha de pagamento.
     * Apenas usuários com a role ADMIN podem realizar esta operação.
     * @param requestDTO Dados da folha de pagamento a ser criada.
     * @return ResponseEntity com o FolhaPagamentoResponseDTO da folha criada e status 201 Created.
     */
    @PostMapping
    public ResponseEntity<FolhaPagamentoResponseDTO> criarFolhaPagamento(@RequestBody @Valid FolhaPagamentoRequestDTO requestDTO) {
        FolhaPagamentoResponseDTO novaFolha = folhaPagamentoService.criarFolhaPagamento(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaFolha);
    }

    /**
     * Busca um registro de folha de pagamento pelo seu ID único.
     * Apenas usuários com a role ADMIN podem realizar esta operação.
     * @param id ID da folha de pagamento a ser buscada.
     * @return ResponseEntity com o FolhaPagamentoResponseDTO da folha encontrada e status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FolhaPagamentoResponseDTO> buscarFolhaPagamentoPorId(@PathVariable UUID id) {
        FolhaPagamentoResponseDTO folha = folhaPagamentoService.buscarFolhaPagamentoPorId(id);
        return ResponseEntity.ok(folha);
    }

    /**
     * Lista todos os registros de folha de pagamento.
     * Apenas usuários com a role ADMIN podem realizar esta operação.
     * @return ResponseEntity com uma lista de FolhaPagamentoResponseDTOs e status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<FolhaPagamentoResponseDTO>> listarTodasFolhasPagamento() {
        List<FolhaPagamentoResponseDTO> folhas = folhaPagamentoService.listarTodasFolhasPagamento();
        return ResponseEntity.ok(folhas);
    }

    /**
     * Busca registros de folha de pagamento por ID do funcionário.
     * Apenas usuários com a role ADMIN podem realizar esta operação.
     * @param funcionarioId O ID do funcionário.
     * @return ResponseEntity com uma lista de FolhaPagamentoResponseDTOs para o funcionário especificado e status 200 OK.
     */
    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<FolhaPagamentoResponseDTO>> buscarFolhasPagamentoPorFuncionario(@PathVariable UUID funcionarioId) {
        List<FolhaPagamentoResponseDTO> folhas = folhaPagamentoService.buscarFolhasPagamentoPorFuncionario(funcionarioId);
        return ResponseEntity.ok(folhas);
    }

    /**
     * Atualiza os dados de um registro de folha de pagamento existente.
     * Apenas usuários com a role ADMIN podem realizar esta operação.
     * @param id ID da folha de pagamento a ser atualizada.
     * @param requestDTO Novos dados da folha de pagamento.
     * @return ResponseEntity com o FolhaPagamentoResponseDTO da folha atualizada e status 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FolhaPagamentoResponseDTO> atualizarFolhaPagamento(@PathVariable UUID id, @RequestBody @Valid FolhaPagamentoRequestDTO requestDTO) {
        FolhaPagamentoResponseDTO folhaAtualizada = folhaPagamentoService.atualizarFolhaPagamento(id, requestDTO);
        return ResponseEntity.ok(folhaAtualizada);
    }

    /**
     * Deleta um registro de folha de pagamento do sistema.
     * Apenas usuários com a role ADMIN podem realizar esta operação.
     * @param id ID da folha de pagamento a ser deletada.
     * @return ResponseEntity com status 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFolhaPagamento(@PathVariable UUID id) {
        folhaPagamentoService.deletarFolhaPagamento(id);
        return ResponseEntity.noContent().build();
    }
}