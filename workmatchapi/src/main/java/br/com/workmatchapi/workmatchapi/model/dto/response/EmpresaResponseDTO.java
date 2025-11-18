package br.com.workmatchapi.workmatchapi.model.dto.response;


public record EmpresaResponseDTO (
        Long id,
        String nome,
        String cnpj,
        String email,
        String telefone
){}
