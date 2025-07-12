package com.academia.service;

import com.academia.entity.Employee.Employee;
import com.academia.entity.Employee.EmployeeReferenceDTO;
import com.academia.entity.FolhaPagamento.FolhaPagamento;
import com.academia.entity.FolhaPagamento.FolhaPagamentoRequestDTO;
import com.academia.entity.FolhaPagamento.FolhaPagamentoResponseDTO;
import com.academia.exception.ResourceNotFoundException;
import com.academia.exception.ValidationException;
import com.academia.repository.EmployeeRepository;
import com.academia.repository.FolhaPagamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FolhaPagamentoService {

    private final FolhaPagamentoRepository folhaPagamentoRepository;
    private final EmployeeRepository employeeRepository;

    public FolhaPagamentoService(FolhaPagamentoRepository folhaPagamentoRepository,
                                 EmployeeRepository employeeRepository) {
        this.folhaPagamentoRepository = folhaPagamentoRepository;
        this.employeeRepository = employeeRepository;
    }

    private FolhaPagamentoResponseDTO convertToResponseDTO(FolhaPagamento folhaPagamento) {
        EmployeeReferenceDTO funcionarioDTO = null;
        if (folhaPagamento.getFuncionario() != null) {
            funcionarioDTO = new EmployeeReferenceDTO(
                    folhaPagamento.getFuncionario().getId(),
                    folhaPagamento.getFuncionario().getNome(),
                    folhaPagamento.getFuncionario().getCpf()
            );
        }

        return new FolhaPagamentoResponseDTO(
                folhaPagamento.getId(),
                funcionarioDTO,
                folhaPagamento.getMesReferencia(),
                folhaPagamento.getAnoReferencia(),
                folhaPagamento.getDataPagamento(),
                folhaPagamento.getSalarioBase(),
                folhaPagamento.getValorTotalLiquido(),
                folhaPagamento.getObservacoes()
        );
    }

    @Transactional
    public FolhaPagamentoResponseDTO criarFolhaPagamento(FolhaPagamentoRequestDTO requestDTO) {
        Employee funcionario = employeeRepository.findById(requestDTO.getFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + requestDTO.getFuncionarioId()));

        if (folhaPagamentoRepository.findByFuncionarioIdAndMesReferenciaAndAnoReferencia(
                requestDTO.getFuncionarioId(), requestDTO.getMesReferencia(), requestDTO.getAnoReferencia()).isPresent()) {
            throw new ValidationException("Já existe uma folha de pagamento para este funcionário no mês/ano de referência especificado.");
        }

        FolhaPagamento folhaPagamento = new FolhaPagamento();
        folhaPagamento.setFuncionario(funcionario);
        folhaPagamento.setMesReferencia(requestDTO.getMesReferencia()); // Usa o enum
        folhaPagamento.setAnoReferencia(requestDTO.getAnoReferencia());
        folhaPagamento.setDataPagamento(requestDTO.getDataPagamento());
        folhaPagamento.setSalarioBase(requestDTO.getSalarioBase());
        folhaPagamento.setValorTotalLiquido(requestDTO.getValorTotalLiquido());
        folhaPagamento.setObservacoes(requestDTO.getObservacoes());

        folhaPagamento = folhaPagamentoRepository.save(folhaPagamento);
        return convertToResponseDTO(folhaPagamento);
    }

    @Transactional(readOnly = true)
    public FolhaPagamentoResponseDTO buscarFolhaPagamentoPorId(UUID id) {
        FolhaPagamento folhaPagamento = folhaPagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Folha de Pagamento não encontrada com ID: " + id));
        if (folhaPagamento.getFuncionario() != null) folhaPagamento.getFuncionario().getId();
        return convertToResponseDTO(folhaPagamento);
    }

    @Transactional(readOnly = true)
    public List<FolhaPagamentoResponseDTO> listarTodasFolhasPagamento() {
        List<FolhaPagamento> folhasPagamento = folhaPagamentoRepository.findAll();
        folhasPagamento.forEach(fp -> {
            if (fp.getFuncionario() != null) fp.getFuncionario().getId();
        });
        return folhasPagamento.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FolhaPagamentoResponseDTO> buscarFolhasPagamentoPorFuncionario(UUID funcionarioId) {
        List<FolhaPagamento> folhasPagamento = folhaPagamentoRepository.findByFuncionarioId(funcionarioId);
        folhasPagamento.forEach(fp -> {
            if (fp.getFuncionario() != null) fp.getFuncionario().getId();
        });
        return folhasPagamento.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FolhaPagamentoResponseDTO atualizarFolhaPagamento(UUID id, FolhaPagamentoRequestDTO requestDTO) {
        FolhaPagamento folhaPagamentoExistente = folhaPagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Folha de Pagamento não encontrada com ID: " + id));

        if (!folhaPagamentoExistente.getFuncionario().getId().equals(requestDTO.getFuncionarioId())) {
            Employee novoFuncionario = employeeRepository.findById(requestDTO.getFuncionarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Novo Funcionário não encontrado com ID: " + requestDTO.getFuncionarioId()));
            folhaPagamentoExistente.setFuncionario(novoFuncionario);
        }

        Optional<FolhaPagamento> folhaExistenteNoMesAno = folhaPagamentoRepository.findByFuncionarioIdAndMesReferenciaAndAnoReferencia(
                requestDTO.getFuncionarioId(), requestDTO.getMesReferencia(), requestDTO.getAnoReferencia());
        if (folhaExistenteNoMesAno.isPresent() && !folhaExistenteNoMesAno.get().getId().equals(id)) {
            throw new ValidationException("Já existe outra folha de pagamento para este funcionário no mês/ano de referência especificado.");
        }

        folhaPagamentoExistente.setMesReferencia(requestDTO.getMesReferencia()); // Usa o enum
        folhaPagamentoExistente.setAnoReferencia(requestDTO.getAnoReferencia());
        folhaPagamentoExistente.setDataPagamento(requestDTO.getDataPagamento());
        folhaPagamentoExistente.setSalarioBase(requestDTO.getSalarioBase());
        folhaPagamentoExistente.setValorTotalLiquido(requestDTO.getValorTotalLiquido());
        folhaPagamentoExistente.setObservacoes(requestDTO.getObservacoes());

        folhaPagamentoExistente = folhaPagamentoRepository.save(folhaPagamentoExistente);
        return convertToResponseDTO(folhaPagamentoExistente);
    }

    @Transactional
    public void deletarFolhaPagamento(UUID id) {
        if (!folhaPagamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Folha de Pagamento não encontrada com ID: " + id);
        }
        folhaPagamentoRepository.deleteById(id);
    }
}
