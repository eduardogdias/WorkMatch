package br.com.workmatchapi.workmatchapi.model.dto.response;

import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import br.com.workmatchapi.workmatchapi.model.enums.Formacao;
import br.com.workmatchapi.workmatchapi.model.enums.NivelIngles;

public record CurriculoResponseDTO(
        Long id,
        Formacao formacao,
        Integer experiencia,
        NivelIngles nivelIngles,
        String estado,
        Usuario usuario
) {}
