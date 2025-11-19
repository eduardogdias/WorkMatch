package br.com.workmatchapi.workmatchapi.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(
        @NotBlank(message = "O email é obrigatório")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha) {
}
