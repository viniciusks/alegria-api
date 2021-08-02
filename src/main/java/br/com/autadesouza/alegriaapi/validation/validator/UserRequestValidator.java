package br.com.autadesouza.alegriaapi.validation.validator;

import br.com.autadesouza.alegriaapi.controller.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class UserRequestValidator {

    @Autowired
    public Validator validator;

    public void validate(final UserRequest userRequest) {
        final var violations = validator.validate(userRequest);

        if(!violations.isEmpty()) {
            throw new RuntimeException("Runtime exception");
        }
    }
}
