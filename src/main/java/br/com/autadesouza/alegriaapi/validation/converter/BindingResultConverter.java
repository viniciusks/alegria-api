package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BindingResultConverter extends AbstractConstraintViolationsConverter<BindingResult>{

    public ErrorResponse toErrorResponse(BindingResult error) {
        final List<ConstraintViolation<?>> violations = error.getFieldErrors()
                .stream()
                .map(this::toConstraintViolation)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return converter(violations);
    }

    private ConstraintViolation<?>  toConstraintViolation(FieldError fe) {
        Field field = ReflectionUtils.findField(fe.getClass(), "violation");
        ReflectionUtils.makeAccessible(field);
        try {
            return (ConstraintViolation<?>) field.get(fe);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Class<BindingResult> getType() {
        return BindingResult.class;
    }
}
