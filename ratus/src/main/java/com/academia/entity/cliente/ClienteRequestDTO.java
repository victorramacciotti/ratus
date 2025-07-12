package com.academia.entity.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "^\\d{11}$", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
    private String cpf;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 255, message = "O nome não pode ter mais de 255 caracteres.")
    private String nome;

    @Email(message = "Formato de e-mail inválido.")
    @Size(max = 255, message = "O e-mail não pode ter mais de 255 caracteres.")
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    @Size(max = 255, message = "O telefone não pode ter mais de 255 caracteres.")
    private String telefone;
}