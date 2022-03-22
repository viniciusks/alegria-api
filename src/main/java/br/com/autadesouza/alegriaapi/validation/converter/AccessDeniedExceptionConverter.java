package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.controller.response.ErrorMessage;
import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import br.com.autadesouza.alegriaapi.utils.MessageProperties;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.FORBIDDEN;

@Component
public class AccessDeniedExceptionConverter
        implements ErrorConverter<AccessDeniedException>{

    @Autowired
    private MessageProperties messageProperties;

    @Override
    public ErrorResponse toErrorResponse(AccessDeniedException error) {
        final var msg = messageProperties.getFormattedMessage(FORBIDDEN);
        final var messageError = new ErrorMessage(FORBIDDEN, msg, null, Lists.newArrayList(msg));

        return new ErrorResponse(Lists.newArrayList(messageError), HttpStatus.FORBIDDEN);
    }

    @Override
    public Class<AccessDeniedException> getType() {
        return AccessDeniedException.class;
    }
}
