package br.com.autadesouza.alegriaapi.validation.exception;

import lombok.Getter;

@Getter
public class MusicaNotFoundException extends RuntimeException {

    public MusicaNotFoundException(String msg) {
        super(msg);
    }

    public MusicaNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
