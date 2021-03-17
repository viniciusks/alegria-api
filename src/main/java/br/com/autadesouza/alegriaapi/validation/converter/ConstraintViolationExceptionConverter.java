package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import br.com.autadesouza.alegriaapi.validation.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

@Component
public class ConstraintViolationExceptionConverter
        extends AbstractConstraintViolationsConverter<ConstraintViolationException>{

    @Override
    public ErrorResponse toErrorResponse(ConstraintViolationException error) {
        return converter(error.getConstraintViolations());
    }

    @Override
    public Class<ConstraintViolationException> getType() {
        return ConstraintViolationException.class;
    }
}
