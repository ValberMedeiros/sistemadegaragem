package br.com.basecmp.sisgaragem.domain.exception;

public class EntidadeEmUsoException extends RuntimeException {

    public EntidadeEmUsoException(String message) {
        super(message);
    }
}
