package br.com.autadesouza.alegriaapi.validation.converter;

import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;

public interface ErrorConverter<T> {

    ErrorResponse toErrorResponse(T error);

    Class<T> getType();
}
