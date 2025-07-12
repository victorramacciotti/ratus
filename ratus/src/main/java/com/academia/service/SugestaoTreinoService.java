package com.academia.service;

import com.academia.entity.SugestaoTreino.SugestaoTreinoRequestDTO;
import com.academia.entity.SugestaoTreino.SugestaoTreinoResponseDTO;
import com.academia.entity.cliente.Cliente;
import com.academia.entity.treino.Treino;
import com.academia.entity.treino.TreinoResponseDTO;
import com.academia.repository.ClienteRepository;
import com.academia.repository.TreinoRepository;
import com.academia.repository.ExecucaoExercicioRepository;
import com.academia.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SugestaoTreinoService {

    private final ClienteRepository clienteRepository;
    private final TreinoRepository treinoRepository;
    private final ExecucaoExercicioRepository execucaoExercicioRepository;
    private final TreinoService treinoService; // Para reutilizar a conversão de Treino para TreinoResponseDTO

    public SugestaoTreinoService(ClienteRepository clienteRepository,
                                 TreinoRepository treinoRepository,
                                 ExecucaoExercicioRepository execucaoExercicioRepository,
                                 TreinoService treinoService) {
        this.clienteRepository = clienteRepository;
        this.treinoRepository = treinoRepository;
        this.execucaoExercicioRepository = execucaoExercicioRepository;
        this.treinoService = treinoService;
    }

    /**
     * Gera uma sugestão de treino personalizada para um cliente.
     * A lógica inicial verifica se o cliente já tem um treino para o dia solicitado.
     * Se não tiver, sugere um treino que ele ainda não faz.
     *
     * @param requestDTO Contém o ID do cliente e, opcionalmente, o dia da semana.
     * @return SugestaoTreinoResponseDTO com a sugestão e uma mensagem.
     * @throws ResourceNotFoundException Se o cliente não for encontrado.
     */
    @Transactional(readOnly = true)
    public SugestaoTreinoResponseDTO sugerirTreino(SugestaoTreinoRequestDTO requestDTO) {
        Cliente cliente = clienteRepository.findById(requestDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + requestDTO.getClienteId()));

        List<TreinoResponseDTO> treinosSugeridosDTO = new ArrayList<>();
        String mensagemSugestao;

        if (requestDTO.getDiaDaSemana() != null) {
            // Tenta encontrar um treino para o cliente no dia da semana solicitado
            Optional<Treino> treinoExistenteNoDia = treinoRepository.findByClienteIdAndDiasDaSemana(
                    cliente.getId(), requestDTO.getDiaDaSemana());

            if (treinoExistenteNoDia.isPresent()) {
                // Se já tem um treino para o dia, sugere o próprio treino
                treinosSugeridosDTO.add(treinoService.buscarTreinoPorId(treinoExistenteNoDia.get().getId()));
                mensagemSugestao = "Você já tem um treino agendado para " + requestDTO.getDiaDaSemana().name() + ". Sugestão: continue com seu plano atual.";
            } else {
                // Se não tem treino para o dia, tenta sugerir um treino que ele ainda não faz
                List<Treino> todosOsTreinos = treinoRepository.findAll();
                List<Treino> treinosDoCliente = cliente.getTreinos(); // Carrega treinos do cliente

                // Filtra treinos que o cliente não tem para o dia solicitado
                List<Treino> treinosNaoDoClienteParaODia = todosOsTreinos.stream()
                        .filter(t -> !treinosDoCliente.contains(t) && t.getDiasDaSemana() == requestDTO.getDiaDaSemana())
                        .collect(Collectors.toList());

                if (!treinosNaoDoClienteParaODia.isEmpty()) {
                    // Sugere o primeiro treino disponível que o cliente não faz para aquele dia
                    treinosSugeridosDTO.add(treinoService.buscarTreinoPorId(treinosNaoDoClienteParaODia.get(0).getId()));
                    mensagemSugestao = "Você não tem treino para " + requestDTO.getDiaDaSemana().name() + ". Sugerimos o treino: " + treinosNaoDoClienteParaODia.get(0).getNome();
                } else {
                    // Se não encontrar um novo treino para o dia, sugere um treino genérico ou o primeiro disponível
                    if (!todosOsTreinos.isEmpty()) {
                        treinosSugeridosDTO.add(treinoService.buscarTreinoPorId(todosOsTreinos.get(0).getId()));
                        mensagemSugestao = "Não encontramos um novo treino específico para " + requestDTO.getDiaDaSemana().name() + ". Que tal tentar o treino: " + todosOsTreinos.get(0).getNome() + "?";
                    } else {
                        mensagemSugestao = "Não há treinos disponíveis para sugestão.";
                    }
                }
            }
        } else {
            // Lógica para quando nenhum dia da semana é especificado (sugestão mais geral)
            // Por exemplo, sugerir um treino que o cliente menos fez ou um treino "padrão"
            List<Treino> todosOsTreinos = treinoRepository.findAll();
            if (!todosOsTreinos.isEmpty()) {
                // Sugere o primeiro treino disponível como uma sugestão geral
                treinosSugeridosDTO.add(treinoService.buscarTreinoPorId(todosOsTreinos.get(0).getId()));
                mensagemSugestao = "Não foi especificado um dia da semana. Sugerimos o treino geral: " + todosOsTreinos.get(0).getNome() + ".";
            } else {
                mensagemSugestao = "Não há treinos disponíveis para sugestão.";
            }
        }

        return new SugestaoTreinoResponseDTO(mensagemSugestao, treinosSugeridosDTO);
    }

    // Futuras melhorias poderiam incluir:
    // - Análise do histórico de ExecucaoExercicio para identificar pontos fracos/fortes.
    // - Sugestão baseada em objetivos do cliente (se avaliações físicas forem implementadas).
    // - Algoritmos de recomendação mais avançados.
}