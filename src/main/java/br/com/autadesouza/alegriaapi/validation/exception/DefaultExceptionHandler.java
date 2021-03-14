package br.com.autadesouza.alegriaapi.validation.exception;

import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import br.com.autadesouza.alegriaapi.validation.converter.ErrorConverterFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice
public class DefaultExceptionHandler {

    private final ErrorConverterFactory errorConverterFactory;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException
            (final MethodArgumentNotValidException ex) {

        final var converter = this.errorConverterFactory.getConverter(BindingResult.class);
        final var errorResponse = converter.toErrorResponse(ex.getBindingResult());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<ErrorResponse> handleConstraintViolationException
            (final ConstraintViolationException ex) {

        final var converter = this.errorConverterFactory.getConverter(ConstraintViolationException.class);
        final var errorResponse = converter.toErrorResponse(ex);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(RequestValidationException.class)
    private ResponseEntity<ErrorResponse> handleRequestValidationException
            (final RequestValidationException ex) {

        final var converter = this.errorConverterFactory.getConverter(RequestValidationException.class);
        final var errorResponse = converter.toErrorResponse(ex);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

}
