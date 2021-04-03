package br.com.autadesouza.alegriaapi.validation.validator;

import br.com.autadesouza.alegriaapi.controller.request.AutorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class AutoresRequestValidator {

    @Autowired
    public Validator validator;

    public void validate(final AutorRequest autorRequest) {
        final var violations = validator.validate(autorRequest);

        if(!violations.isEmpty()) {
            throw new RuntimeException("Runtime exception");
        }
    }
}
