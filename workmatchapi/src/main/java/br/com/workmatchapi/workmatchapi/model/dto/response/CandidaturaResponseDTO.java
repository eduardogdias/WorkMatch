package br.com.workmatchapi.workmatchapi.model.dto.response;

import br.com.workmatchapi.workmatchapi.model.entity.Curriculo;
import br.com.workmatchapi.workmatchapi.model.entity.Vaga;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record CandidaturaResponseDTO(
        Long id,
        Vaga vaga,
        Curriculo curriculo,
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date dataCandidatura
) {
}
