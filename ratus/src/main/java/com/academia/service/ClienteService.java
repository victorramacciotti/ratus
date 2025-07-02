package com.academia.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.academia.entity.Cliente;
import com.academia.repository.ClienteRepository;
import com.academia.utils.StringUtils;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    //Cria um novo cliente
    public Cliente createCliente(Cliente cliente){
        if(cliente.getCpf() == null || cliente.getEmail() == null || cliente.getCpf().isBlank() || cliente.getEmail().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os campos de CPF ou Email não podem estar vazios ou ser nulos");
        } 
        if(!StringUtils.isCpf(cliente.getCpf())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CPF digitado é inválido");
        }
        if(this.clienteRepository.existsByEmail(cliente.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }
        if(this.clienteRepository.existsByCpf(StringUtils.parseCpf(cliente.getCpf()))){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado");
        }
        cliente.setCpf(StringUtils.parseCpf(cliente.getCpf()));
        cliente.setTelefone(StringUtils.parseTelefone(cliente.getTelefone()));
        cliente.setEmail(StringUtils.parseEmail(cliente.getEmail()));
        return this.clienteRepository.save(cliente);
    }

    //Lista todos os clientes criados
    public List<Cliente> getClientes(){
        return this.clienteRepository.findAll();
    }

    //Deleta um cliente com base no CPF
    public void deleteClienteByCPF(String cpf){
        if(cpf == null || cpf.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"O campo de CPF pode estar vazio");
        }
        if(!StringUtils.isCpf(cpf)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CPF digitado é inválido");
        }
        if(!this.clienteRepository.existsByCpf(StringUtils.parseCpf(cpf))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CPF não existe na base de dados");
        }
        Cliente deleteCliente = this.clienteRepository.findByCpf(StringUtils.parseCpf(cpf))
         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
         
        this.clienteRepository.delete(deleteCliente);
    }
}
