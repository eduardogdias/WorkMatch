package br.com.workmatchapi.workmatchapi.model.exception;

import org.springframework.http.HttpStatus;

public record ExceptionResponseDTO(
        HttpStatus httpStatus,
        String mensagem
) {
}
