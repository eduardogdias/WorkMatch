package br.com.workmatchapi.workmatchapi.model.dto.response;


public record EmpresaResponseDTO (
        String nome,
        String cnpj,
        String email,
        String telefone
){}
