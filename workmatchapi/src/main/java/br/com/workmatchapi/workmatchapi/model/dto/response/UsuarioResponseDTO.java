package br.com.workmatchapi.workmatchapi.model.dto.response;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf
) {
}
