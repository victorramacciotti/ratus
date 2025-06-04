package com.academia.controller;

import com.academia.entity.Pagamento;
import com.academia.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public List<Pagamento> getAllPagamentos() {
        return pagamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable Long id) {
        Optional<Pagamento> pagamento = pagamentoService.findById(id);
        return pagamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pagamento createPagamento(@RequestBody Pagamento pagamento) {
        // Validações podem ser adicionadas aqui (ex: verificar se cliente existe)
        return pagamentoService.save(pagamento);
    }

    // PUT (atualização) para Pagamento pode não ser comum, talvez apenas DELETE
    // Se necessário, implementar PUT similar aos outros controllers

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePagamento(@PathVariable Long id) {
        if (pagamentoService.findById(id).isPresent()) {
            pagamentoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoints adicionais podem ser úteis, como buscar pagamentos de um cliente específico.
}

