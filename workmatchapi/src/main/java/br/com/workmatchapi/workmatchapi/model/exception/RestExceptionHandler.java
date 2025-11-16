package br.com.workmatchapi.workmatchapi.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontrada.class)
    private ResponseEntity<ExceptionResponseDTO> eventoEntidadeNaoEncontrada(EntidadeNaoEncontrada exception){
        ExceptionResponseDTO mensagemErro = new ExceptionResponseDTO(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
    }

}
