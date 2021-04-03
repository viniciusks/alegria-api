package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.controller.response.ErrorMessage;
import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import br.com.autadesouza.alegriaapi.utils.MessageProperties;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import br.com.autadesouza.alegriaapi.validation.annotation.StringMatchWithEnum;
import br.com.autadesouza.alegriaapi.validation.annotation.Values;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.metadata.ConstraintDescriptor;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.*;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static com.google.common.collect.Lists.newArrayList;

public abstract class AbstractExceptionConverter<T> implements ErrorConverter<T> {

    @Autowired
    private MessageProperties messageProperties;

    private final Class<T> converterClass;

    public AbstractExceptionConverter() {
        converterClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
        .getActualTypeArguments()[0];
    }

    @Override
    public ErrorResponse toErrorResponse(T error) {
        final var description = messageProperties.getFormattedMessage(getDescriptionKey());
        final var msg = messageProperties.getFormattedMessage(getMessageKey());
        final var messageError = new ErrorMessage(getCode(), description, getParameterName(), newArrayList(msg));

        return new ErrorResponse(newArrayList(messageError), getStatusCode());
    }

    @Override
    public final Class<T> getType() {
        return converterClass;
    }

    protected abstract String getDescriptionKey();

    protected abstract String getMessageKey();

    protected abstract String getCode();

    protected abstract HttpStatus getStatusCode();

    protected abstract String getParameterName();
}
