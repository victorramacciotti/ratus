package com.academia.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.entity.Cliente;
import com.academia.service.ClienteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping("path")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
        Cliente savedCliente = this.clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }

    @GetMapping("path")
    public List<Cliente> getClientes(){
       return this.clienteService.getClientes();
    }

    @DeleteMapping("{cpf}")
    public void deleteClienteByCPF(@PathVariable String cpf){
        this.clienteService.deleteClienteByCPF(cpf);
    }

}
