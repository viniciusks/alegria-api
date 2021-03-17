package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.controller.response.ErrorMessage;
import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import br.com.autadesouza.alegriaapi.utils.MessageProperties;
import br.com.autadesouza.alegriaapi.validation.exception.RequestValidationException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RequestValidatorExceptionConverter implements ErrorConverter<RequestValidationException> {

    @Autowired
    private MessageProperties messageProperties;

    @Override
    public ErrorResponse toErrorResponse(RequestValidationException ex) {
        final var code = ex.getCode();
        final var message = new ErrorMessage(code, messageProperties.getFormattedMessage(code), ex.getParameterName(),
                Lists.newArrayList(messageProperties.getFormattedMessage(ex.getMessage())));

        return new ErrorResponse(Lists.newArrayList(message), HttpStatus.BAD_REQUEST);
    }

    @Override
    public Class<RequestValidationException> getType() {
        return RequestValidationException.class;
    }
}
