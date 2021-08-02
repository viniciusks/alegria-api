package br.com.autadesouza.alegriaapi.validation.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }

    public UserAlreadyExistsException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
