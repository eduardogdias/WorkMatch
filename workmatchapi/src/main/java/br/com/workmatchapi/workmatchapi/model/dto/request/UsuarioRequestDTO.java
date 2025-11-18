package br.com.workmatchapi.workmatchapi.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UsuarioRequestDTO (

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O email é obrigatório")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        @Length(min = 11, max = 11, message = "O telefone deve ter exatamente 11 dígitos")
        String telefone,

        @NotBlank(message = "O CPF é obrigatório")
        @Length(min = 11, max = 11, message = "O CPF deve ter exatamente 11 dígitos")
        String cpf,

        @NotBlank(message = "A senha é obrigatória")
        String senha

){
}
