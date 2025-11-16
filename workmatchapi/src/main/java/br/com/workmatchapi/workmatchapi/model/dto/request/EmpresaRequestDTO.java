package br.com.workmatchapi.workmatchapi.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record EmpresaRequestDTO (
        @NotBlank(message = "Nome da empresa é obirgatório")
        String nome,
        @Length(min = 14, max = 14, message = "CNPJ precisa ter 14 digitos")
        String cnpj,
        @Email(message = "Email deve ser válido")
        String email,
        @Length(min = 11, max = 11, message = "O telefone deve ter exatamente 11 dígitos")
        String telefone
){}
