package com.academia.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class StringUtils {
     private static final CPFValidator cpfValidator = new CPFValidator();

    //Verifica se CPF é válido
    public static boolean isCpf(String cpf){
        try {
            cpfValidator.assertValid(parseCpf(cpf));
            return true;
        } catch (InvalidStateException e){
            return false;
        } 
    }

    //Remove todos os caracteres especiais do CPF digitado se houver e verifica se o tamanho é válido
    public static String parseCpf(String cpf){
        String newCPF = cpf.replaceAll("[^\\d]", "");
        if(newCPF.length() != 11){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A quantidade de caracteres digitada é diferente de 11, portanto não corresponde a um CPF");
        }
        return newCPF;
    }

    //Remove todos os caracteres especiais do Telefone digitado se houver e verifica se o tamanho é válido 
    public static String parseTelefone(String telefone){
        String newTelefone = telefone.replaceAll("[^\\d]", "");
        if(newTelefone.length() < 10 || newTelefone.length() > 13){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O número de telefone deve ter entre 10 a 13 caracteres, portanto não corresponde a um Telefone");
        }
        return newTelefone;
    }

    //Formata uma String de Email para verificar se a mesma é um Email válido
    public static String parseEmail(String email){
        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O dado não corresponde a um Email");
        }
        return email;
    }
}
