package br.com.autadesouza.alegriaapi.validation.exception;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ConstraintViolationException extends RuntimeException {

    private static final long serialVersionUID = -1376925803279073816L;

    private final Set<ConstraintViolation<?>> constraintViolations = new HashSet<>();

    public ConstraintViolationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(toString(constraintViolations));
        this.constraintViolations.addAll(constraintViolations);
    }

    public Set<ConstraintViolation<?>> getConstraintViolations() {
        return this.constraintViolations;
    }

    private static String toString(Set<? extends ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream()
                .map(cv -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining(", "));
    }
}
