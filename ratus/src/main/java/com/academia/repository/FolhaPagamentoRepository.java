package com.academia.repository;

import com.academia.entity.FolhaPagamento.FolhaPagamento;
import com.academia.enums.MesReferenteAPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FolhaPagamentoRepository extends JpaRepository<FolhaPagamento, UUID> {
    // Método para buscar folhas de pagamento por funcionário e mês/ano de referência
    Optional<FolhaPagamento> findByFuncionarioIdAndMesReferenciaAndAnoReferencia(UUID funcionarioId, MesReferenteAPagamento mesReferencia, int anoReferencia); // Assinatura atualizada

    // Método para buscar todas as folhas de pagamento de um funcionário
    List<FolhaPagamento> findByFuncionarioId(UUID funcionarioId);
}
