package br.com.workmatchapi.workmatchapi.model.exception;

public class EntidadeNaoEncontrada extends RuntimeException {

    public EntidadeNaoEncontrada(){
        super("Entidade não encontrada");
    }

    public EntidadeNaoEncontrada(String message) {
        super(message);
    }
}
