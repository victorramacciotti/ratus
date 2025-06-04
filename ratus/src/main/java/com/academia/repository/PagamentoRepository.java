package com.academia.repository;

import com.academia.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
    // Exemplo: List<Pagamento> findByClienteId(Long clienteId);
}

