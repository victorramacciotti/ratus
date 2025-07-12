package com.academia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // Retorna 400 Bad Request por padrão
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}