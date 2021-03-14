package br.com.autadesouza.alegriaapi.validation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Stream;

public class StringMatchWithEnumValidator implements ConstraintValidator<StringMatchWithEnum, String> {

    private Class<? extends Enum> type;

    @Override
    public void initialize(StringMatchWithEnum constraintAnnotation) {
        type = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value != null) {
            return Stream.of(type.getEnumConstants()).map(Enum::name).anyMatch(name -> name.equalsIgnoreCase(value));
        }
        return true;
    }
}
