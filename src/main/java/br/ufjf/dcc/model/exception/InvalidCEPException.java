package br.ufjf.dcc.model.exception;

public class InvalidCEPException extends RuntimeException {
    public InvalidCEPException(String message) {
        super(message);
    }
}
