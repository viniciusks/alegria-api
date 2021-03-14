package br.com.autadesouza.alegriaapi.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final List<ErrorMessage> errorMessages;

    @JsonIgnore
    private final HttpStatus httpStatus;
}
