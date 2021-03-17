package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.controller.response.ErrorMessage;
import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import br.com.autadesouza.alegriaapi.utils.MessageProperties;
import br.com.autadesouza.alegriaapi.validation.exception.ResourceNotFoundException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.NOT_FOUND;

@Component
public class ResourceNotFoundExceptionConverter implements ErrorConverter<ResourceNotFoundException> {

    @Autowired
    private MessageProperties messageProperties;

    @Override
    public ErrorResponse toErrorResponse(ResourceNotFoundException error) {
        final var msg = messageProperties.getFormattedMessage(NOT_FOUND);
        final var messageError = new ErrorMessage(NOT_FOUND, msg, null, Lists.newArrayList(msg));

        return new ErrorResponse(Lists.newArrayList(messageError), HttpStatus.NOT_FOUND);
    }

    @Override
    public Class<ResourceNotFoundException> getType() {
        return ResourceNotFoundException.class;
    }
}
