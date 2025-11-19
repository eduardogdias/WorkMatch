package br.com.workmatchapi.workmatchapi.model.dto.request;

import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import br.com.workmatchapi.workmatchapi.model.enums.Estado;
import br.com.workmatchapi.workmatchapi.model.enums.Formacao;
import br.com.workmatchapi.workmatchapi.model.enums.NivelIngles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record CurriculoRequestDTO(
        @NotNull(message = "A formação é obrigatória")
        Formacao formacao,

        @NotNull(message = "A experiencia é obrigatória")
        @PositiveOrZero(message = "Os anos de experiência devem ser maior ou igual a 0")
        int experiencia,

        @NotNull(message = "O nível de Inglês é obrigatório")
        NivelIngles nivelIngles,

        @NotNull(message = "O Estado é obrigatório")
        Estado estado,

        @NotNull(message = "A lista de skills não pode ser nula")
        List<String> skills,

        @NotNull(message = "O ID da empresa é obrigatório")
        @Positive(message = "O ID da empresa deve ser positivo")
        Long usuarioId
) {
}
