package br.com.autadesouza.alegriaapi.controller.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Getter
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@JsonPropertyOrder({"code", "parameter_name", "description", "errors"})
public class ErrorMessage {

    private final String code;

    private final String description;

    private final String parameterName;

    private final Collection<String> erros;
}
