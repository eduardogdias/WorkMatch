package br.com.workmatchapi.workmatchapi.model.dto.response;

import br.com.workmatchapi.workmatchapi.model.entity.Empresa;
import br.com.workmatchapi.workmatchapi.model.enums.Estado;
import br.com.workmatchapi.workmatchapi.model.enums.Formacao;
import br.com.workmatchapi.workmatchapi.model.enums.ModeloTrabalho;
import br.com.workmatchapi.workmatchapi.model.enums.NivelIngles;
import jakarta.validation.constraints.*;

import java.util.Date;


public record VagaResponseDTO(
        String cargo,
        Date dataFim,
        int experiencia,
        NivelIngles nivelIngles,
        ModeloTrabalho modeloTrabalho,
        Formacao formacao,
        Estado estado,
        int match,
        Empresa empresa
) {}

