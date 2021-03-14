package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.controller.response.ErrorMessage;
import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import br.com.autadesouza.alegriaapi.utils.MessageProperties;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import br.com.autadesouza.alegriaapi.validation.annotation.StringMatchWithEnum;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static br.com.autadesouza.alegriaapi.utils.CodeValidationsConstants.*;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

public abstract class AbstractConstraintViolationsConverter<T> implements ErrorConverter<T> {

    public static final String AFTER_ENUM_CLASS = "enumClass";

    @Autowired
    private MessageProperties messageProperties;

    @Autowired
    private ConstraintViolationWrapper constraintViolationWrapper;

    protected ErrorResponse converter(final Collection<ConstraintViolation<?>> violations) {
        final Multimap<String, String> map = ArrayListMultimap.create();

        final var entries = violations
                .stream()
                .map(this::toEntry)
                .collect(Collectors.toList());

        entries.stream()
                .forEach(entry -> map.put(entry.getKey(), entry.getValue()));

        final var errors = map.asMap()
                .entrySet()
                .stream()
                .map(this::createErrorMessage)
                .collect(Collectors.toList());

        errors.sort(Comparator.comparing(ErrorMessage::getCode));

        return new ErrorResponse(errors, HttpStatus.BAD_REQUEST);
    }

    private String getConcatenedEnumTypes(Class enumType) {
        return Arrays.stream(enumType.getEnumConstants())
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    private boolean isStringMatchWithEnumAnnotation(ConstraintDescriptor<?> constraintDescriptor) {
        return StringMatchWithEnum.class.isAssignableFrom(constraintDescriptor.getAnnotation().annotationType());
    }

    private ErrorMessage createErrorMessage(final Entry<String, Collection<String>> entry) {

        final var key = entry.getKey().split(":");
        final var code = key[0];
        final var parameterName = key[1];
        final var description = messageProperties.getFormattedMessage(code);

        return new ErrorMessage(code, description, parameterName, entry.getValue());
    }

    private Entry<String, String> toEntry(ConstraintViolation<?> violation) {
        final var key = getErrorCode(this.constraintViolationWrapper.getGroups(violation)) + ":"
                + getParameterName(violation);

        var rootBean = violation.getRootBean();
        var msg = getMessage(violation, rootBean);

        return new AbstractMap.SimpleImmutableEntry<>(key, msg);
    }

    private String getErrorCode(Set<Class<?>> groups) {
        if(containsMandatories(groups)) {
            return REQUIRED_PARAMETER;
        }

        if(containsMandatories(groups)) {
            return INVALID_PARAMETER;
        }

        return UNMAPPED_ERROR;
    }

    private String getMessage(ConstraintViolation<?> violation, Object rootBean) {

        var constraintDescriptor = violation.getConstraintDescriptor();
        if(isStringMatchWithEnumAnnotation(constraintDescriptor)) {
            var enumType = (Class) constraintDescriptor.getAttributes().get(AFTER_ENUM_CLASS);
            var types = getConcatenedEnumTypes(enumType);
            return this.constraintViolationWrapper.getMessage(violation).replace("{enumValues}", types);
        }

        return this.constraintViolationWrapper.getMessage(violation);
    }

    private boolean containsMandatories(final Set<Class<?>> groups) {
        final Set<Class> mandatories = Sets.newHashSet(Mandatory.class);
        return mandatories.stream().anyMatch(groups::contains);
    }

    private String getParameterName(final ConstraintViolation<?> violation) {
        final List<String> parameterName = Lists.newArrayList();

        try {
            var parentClass = this.constraintViolationWrapper.getRootBean(violation).getClass();

            final var jsonRootName = parentClass.getAnnotation(JsonRootName.class);

            if(Objects.nonNull(jsonRootName)) {
                parameterName.add(jsonRootName.value());
            }

            var path = violation.getPropertyPath().toString();
            parameterName.add(UPPER_CAMEL.to(LOWER_UNDERSCORE, path));
            return parameterName.stream().collect(Collectors.joining("."));
        } catch (Exception e) {
            return this.constraintViolationWrapper.getPath(violation);
        }
    }

}
