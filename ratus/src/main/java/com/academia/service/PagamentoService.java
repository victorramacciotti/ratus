package com.academia.service;

import com.academia.entity.Pagamento;
import com.academia.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> findAll() {
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> findById(Long id) {
        return pagamentoRepository.findById(id);
    }

    public Pagamento save(Pagamento pagamento) {
        // Adicionar validações ou lógica de negócio antes de salvar, se necessário
        // Por exemplo, verificar se o cliente existe
        return pagamentoRepository.save(pagamento);
    }

    public void deleteById(Long id) {
        // Adicionar validações ou lógica de negócio antes de deletar, se necessário
        pagamentoRepository.deleteById(id);
    }

    // Outros métodos de serviço relacionados a Pagamento podem ser adicionados aqui
    // Exemplo: buscarPagamentosPorCliente, etc.
}

