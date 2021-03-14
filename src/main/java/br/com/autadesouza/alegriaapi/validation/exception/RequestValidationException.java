package br.com.autadesouza.alegriaapi.validation.exception;

import lombok.Getter;

@Getter
public class RequestValidationException extends RuntimeException {

    private final String code;

    private final String parameterName;

    public RequestValidationException(String code, String message, String parameterName) {
        super(message);
        this.code = code;
        this.parameterName = parameterName;
    }
}
