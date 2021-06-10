package br.com.autadesouza.alegriaapi.validation.exception;

public class LetraNotFoundException extends RuntimeException {

    public LetraNotFoundException(String msg) {
        super(msg);
    }

    public LetraNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
