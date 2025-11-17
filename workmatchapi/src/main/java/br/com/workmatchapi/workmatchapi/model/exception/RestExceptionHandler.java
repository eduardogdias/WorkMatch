package br.com.workmatchapi.workmatchapi.model.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontrada.class)
    private ResponseEntity<ExceptionResponseDTO> eventoEntidadeNaoEncontrada(EntidadeNaoEncontrada exception){
        ExceptionResponseDTO mensagemErro = new ExceptionResponseDTO(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        StringBuilder mensagem = new StringBuilder("Erro(s) de validação: ");

        exception.getBindingResult().getFieldErrors().forEach(err -> {
            mensagem.append(err.getField())
                    .append(": ")
                    .append(err.getDefaultMessage())
                    .append("; ");
        });

        ExceptionResponseDTO mensagemErro = new ExceptionResponseDTO(HttpStatus.BAD_REQUEST, mensagem.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
    }

}
