package br.com.autadesouza.alegriaapi.validation.exception;

public class GeneroNotFoundException extends RuntimeException {

    public GeneroNotFoundException(String msg) {
        super(msg);
    }

    public GeneroNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
