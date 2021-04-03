package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.validation.exception.GeneroNotFoundException;
import br.com.autadesouza.alegriaapi.validation.exception.MusicaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
public class GeneroNotFoundExceptionConverter extends AbstractExceptionConverter<GeneroNotFoundException> {

    private final static String GENERO_NOT_FOUND = "genero_not_found";

    @Override
    protected String getDescriptionKey() {
        return NOT_FOUND;
    }

    @Override
    protected String getMessageKey() {
        return GENERO_NOT_FOUND;
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
