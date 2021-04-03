package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.validation.exception.AutorNotFoundException;
import br.com.autadesouza.alegriaapi.validation.exception.GeneroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
public class AutorNotFoundExceptionConverter extends AbstractExceptionConverter<AutorNotFoundException> {

    private final static String AUTOR_NOT_FOUND = "autor_not_found";

    @Override
    protected String getDescriptionKey() {
        return NOT_FOUND;
    }

    @Override
    protected String getMessageKey() {
        return AUTOR_NOT_FOUND;
    }

    @Override
    protected String getCode() {
        return NOT_FOUND;
    }

    @Override
    protected HttpStatus getStatusCode() {
        return UNPROCESSABLE_ENTITY;
    }

    @Override
    protected String getParameterName() {
        return null;
    }
}
