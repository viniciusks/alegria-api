package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.controller.response.ErrorMessage;
import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import br.com.autadesouza.alegriaapi.utils.MessageProperties;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.METHOD_NOT_ALLOWED;

@Component
public class HttpRequestMethodNotSupportedExceptionConverter
        implements ErrorConverter<HttpRequestMethodNotSupportedException>{

    @Autowired
    private MessageProperties messageProperties;

    @Override
    public ErrorResponse toErrorResponse(HttpRequestMethodNotSupportedException error) {
        final var msg = messageProperties.getFormattedMessage(METHOD_NOT_ALLOWED);
        final var messageError = new ErrorMessage(METHOD_NOT_ALLOWED, msg, null, Lists.newArrayList(msg));

        return new ErrorResponse(Lists.newArrayList(messageError), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public Class<HttpRequestMethodNotSupportedException> getType() {
        return HttpRequestMethodNotSupportedException.class;
    }
}
