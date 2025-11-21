package br.com.workmatchapi.workmatchapi.model.dto.request;

import br.com.workmatchapi.workmatchapi.model.enums.Estado;
import br.com.workmatchapi.workmatchapi.model.enums.Formacao;
import br.com.workmatchapi.workmatchapi.model.enums.ModeloTrabalho;
import br.com.workmatchapi.workmatchapi.model.enums.NivelIngles;
import jakarta.validation.constraints.*;


import java.util.Date;
import java.util.List;


public record VagaRequestDTO(

        @NotBlank(message = "O cargo é obrigatório")
        String cargo,

        @NotBlank(message = "A descrição é obrigatório")
        String descricao,

        @NotNull(message = "O salário é obrigatório")
        @Positive(message = "O salário deve ser maior que R$0,00")
        Double salario,

        @NotNull(message = "A data de término da vaga é obrigatória")
        @Future(message = "A data de término precisa ser futura")
        Date dataFim,

        @PositiveOrZero(message = "Os anos de experiência devem ser maior ou igual a 0")
        int experiencia,

        @NotNull(message = "O nível de inglês é obrigatório")
        NivelIngles nivelIngles,

        @NotNull(message = "O modelo de trabalho é obrigatório")
        ModeloTrabalho modeloTrabalho,

        @NotNull(message = "A formação é obrigatória")
        Formacao formacao,

        @NotNull(message = "O estado é obrigatório")
        Estado estado,

        @Min(value = 0, message = "O match mínimo é 0%")
        @Max(value = 100, message = "O match máximo é 100%")
        int match,

        @NotNull(message = "A lista de skills não pode ser nula")
        List<String> skills,

        @NotNull(message = "O ID da empresa é obrigatório")
        @Positive(message = "O ID da empresa deve ser positivo")
        Long empresaId
) {}

