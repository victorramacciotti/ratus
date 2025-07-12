package com.academia.repository; // Sugestão de pacote para Repositories

import com.academia.entity.ExecucaoExercicio.ExecucaoExercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExecucaoExercicioRepository extends JpaRepository<ExecucaoExercicio, UUID> {

    List<ExecucaoExercicio> findByClienteId(UUID clienteId);

    List<ExecucaoExercicio> findByExercicioTreinoId(UUID exercicioTreinoId);

    // Você pode adicionar outros métodos de busca conforme a necessidade, por exemplo:
    // List<ExecucaoExercicio> findByClienteIdAndDataExecucaoBetween(UUID clienteId, LocalDateTime startDate, LocalDateTime endDate);
}
