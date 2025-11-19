package br.com.workmatchapi.workmatchapi.model.dto.response;

import br.com.workmatchapi.workmatchapi.model.entity.Empresa;
import br.com.workmatchapi.workmatchapi.model.enums.Estado;
import br.com.workmatchapi.workmatchapi.model.enums.Formacao;
import br.com.workmatchapi.workmatchapi.model.enums.ModeloTrabalho;
import br.com.workmatchapi.workmatchapi.model.enums.NivelIngles;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;


public record VagaResponseDTO(
        Long id,
        String cargo,
        Double salario,
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date dataFim,
        int experiencia,
        NivelIngles nivelIngles,
        ModeloTrabalho modeloTrabalho,
        Formacao formacao,
        String estado,
        int match,
        List<String> skills,
        Empresa empresa
) {}

