package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.controller.response.ErrorMessage;
import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import br.com.autadesouza.alegriaapi.utils.MessageProperties;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.METHOD_NOT_ALLOWED;
import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.UNSUPPORTED_MEDIA_TYPE;

@Component
public class HttpMediaTypeNotSupportedExceptionConverter
        implements ErrorConverter<HttpMediaTypeNotSupportedException>{

    @Autowired
    private MessageProperties messageProperties;

    @Override
    public ErrorResponse toErrorResponse(HttpMediaTypeNotSupportedException error) {
        final var msg = messageProperties.getFormattedMessage(UNSUPPORTED_MEDIA_TYPE);
        final var messageError = new ErrorMessage(UNSUPPORTED_MEDIA_TYPE, msg, null, Lists.newArrayList(msg));

        return new ErrorResponse(Lists.newArrayList(messageError), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Override
    public Class<HttpMediaTypeNotSupportedException> getType() {
        return HttpMediaTypeNotSupportedException.class;
    }
}
