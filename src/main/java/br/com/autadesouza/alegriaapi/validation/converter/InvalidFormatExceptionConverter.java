package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.controller.response.ErrorMessage;
import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants;
import br.com.autadesouza.alegriaapi.utils.MessageProperties;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.JsonMappingException.Reference;

@Component
public class InvalidFormatExceptionConverter implements ErrorConverter<InvalidFormatException> {

    @Autowired
    private MessageProperties messageProperties;

    @Override
    public ErrorResponse toErrorResponse(InvalidFormatException error) {

        final var parameterName = error.getPath().stream()
                .map(getPath())
                .collect(Collectors.joining("."))
                .replaceAll(".\\[", "[");

        final var code = CodeValidationsConstants.INVALID_PARAMETER;
        final var description = messageProperties.getFormattedMessage(code);
        final var errors = Lists.newArrayList(getMessage(error));

        return new ErrorResponse(Lists.newArrayList(new ErrorMessage(code, description, parameterName, errors)),
                HttpStatus.BAD_REQUEST);
    }

    private String getMessage(InvalidFormatException error) {
        if(error.getTargetType().isEnum()) {
            var values = getConcatenedEnumTypes(error);
            return messageProperties.getFormattedMessage("specific_invalid_format", new Object[] {values});
        }
        return messageProperties.getFormattedMessage("invalid_format");
    }

    private String getConcatenedEnumTypes(InvalidFormatException error) {
        return Arrays.stream(error.getTargetType().getEnumConstants())
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    private Function<Reference, String> getPath() {
        return reference -> {
            if(List.class.isAssignableFrom(reference.getFrom().getClass())) {
                return "[" + reference.getIndex() + "]";
            }
            if(Collection.class.isAssignableFrom(reference.getFrom().getClass())) {
                return "[]";
            }

            return reference.getFieldName();
        };
    }

    @Override
    public Class<InvalidFormatException> getType() {
        return InvalidFormatException.class;
    }
}
