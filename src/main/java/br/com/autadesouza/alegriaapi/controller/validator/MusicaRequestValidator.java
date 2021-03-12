package br.com.autadesouza.alegriaapi.controller.validator;

import br.com.autadesouza.alegriaapi.controller.request.MusicaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class MusicaRequestValidator {

    @Autowired
    public Validator validator;

    public void validate(final MusicaRequest musicaRequest) {
        final var violations = validator.validate(musicaRequest);

        if(!violations.isEmpty()) {
            throw new RuntimeException("Runtime exception");
        }
    }
}
