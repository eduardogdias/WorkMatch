package br.com.workmatchapi.workmatchapi.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record CandidaturaRequestDTO(
        @NotNull(message = "O ID da vaga é obrigatório")
        @Positive(message = "O ID da vaga deve ser positivo")
        Long vagaId,

        @NotNull(message = "O ID do currículo obrigatório")
        @Positive(message = "O ID do currículo deve ser positivo")
        Long curriculoId
) {
}
