package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.controller.response.ErrorMessage;
import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import br.com.autadesouza.alegriaapi.utils.MessageProperties;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;

import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.FORBIDDEN;
import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.UNSUPPORTED_MEDIA_TYPE;

@Component
public class MissingRequestHeaderExceptionConverter
        implements ErrorConverter<MissingRequestHeaderException>{

    @Autowired
    private MessageProperties messageProperties;

    @Override
    public ErrorResponse toErrorResponse(MissingRequestHeaderException error) {
        final var msg = messageProperties.getFormattedMessage(FORBIDDEN);
        final var messageError = new ErrorMessage(FORBIDDEN, msg, null, Lists.newArrayList(msg));

        return new ErrorResponse(Lists.newArrayList(messageError), HttpStatus.FORBIDDEN);
    }

    @Override
    public Class<MissingRequestHeaderException> getType() {
        return MissingRequestHeaderException.class;
    }
}
