package br.com.autadesouza.alegriaapi.validation.exception;

public class AutorNotFoundException extends RuntimeException {

    public AutorNotFoundException(String msg) {
        super(msg);
    }

    public AutorNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
