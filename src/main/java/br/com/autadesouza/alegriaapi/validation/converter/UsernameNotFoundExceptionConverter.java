package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.validation.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
public class UsernameNotFoundExceptionConverter extends AbstractExceptionConverter<UsernameNotFoundException> {

    private final static String USERNAME_NOT_FOUND = "username_not_found";

    @Override
    protected String getDescriptionKey() {
        return NOT_FOUND;
    }

    @Override
    protected String getMessageKey() {
        return USERNAME_NOT_FOUND;
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
