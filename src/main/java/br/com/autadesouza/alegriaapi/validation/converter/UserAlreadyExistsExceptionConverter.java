package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.validation.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
public class UserAlreadyExistsExceptionConverter extends AbstractExceptionConverter<UserAlreadyExistsException> {

    private final static String USER_ALREADY_EXISTS = "user_already_exists";

    @Override
    protected String getDescriptionKey() {
        return NOT_FOUND;
    }

    @Override
    protected String getMessageKey() {
        return USER_ALREADY_EXISTS;
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
