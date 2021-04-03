package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.validation.exception.MusicaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
public class MusicaNotFoundExceptionConverter extends AbstractExceptionConverter<MusicaNotFoundException> {

    private final static String MUSICA_NOT_FOUND = "musica_not_found";

    @Override
    protected String getDescriptionKey() {
        return NOT_FOUND;
    }

    @Override
    protected String getMessageKey() {
        return MUSICA_NOT_FOUND;
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
