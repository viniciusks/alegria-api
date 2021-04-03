package br.com.autadesouza.alegriaapi.validation.exception;

import br.com.autadesouza.alegriaapi.controller.response.ErrorResponse;
import br.com.autadesouza.alegriaapi.validation.converter.ErrorConverterFactory;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.apache.logging.log4j.Level.*;
import static org.springframework.http.HttpStatus.*;

@Log4j2
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException
            (final HttpMessageNotReadableException ex) {

        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException) {
            final var converter = this.errorConverterFactory.getConverter(InvalidFormatException.class);
            final var errorResponse = converter.toErrorResponse((InvalidFormatException) cause);

            return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
        }

        return new ResponseEntity<>(BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleResourceNotFoundException
            (final ResourceNotFoundException ex) {

        final var converter = this.errorConverterFactory.getConverter(ResourceNotFoundException.class);
        final var errorResponse = converter.toErrorResponse(ex);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ResponseEntity<Void> handleHttpRequestMethodNotSupportedException
            (final HttpRequestMethodNotSupportedException ex) {

        return handleException("handleHttpRequestMethodNotSupportedException", METHOD_NOT_ALLOWED, ex,
                HttpRequestMethodNotSupportedException.class, INFO);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    private ResponseEntity<Void> handleHttpMediaTypeNotSupportedException(final HttpMediaTypeNotSupportedException ex) {

        return handleException("handleHttpMediaTypeNotSupportedException", UNSUPPORTED_MEDIA_TYPE, ex,
                HttpMediaTypeNotSupportedException.class, INFO);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    private ResponseEntity<Void> handleMissingRequestHeaderException(final MissingRequestHeaderException ex) {

        return handleException("handleMissingRequestHeaderException", FORBIDDEN, ex,
                MissingRequestHeaderException.class, INFO);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Void> handleAllException(final Exception ex) {
        return handleException("handleMissingRequestHeaderException", INTERNAL_SERVER_ERROR, ex,
                Exception.class, ERROR);
    }

    private ResponseEntity<Void> handleException(final String method, final HttpStatus status,
                                                 final Exception ex, Class<?> exceptionClass, Level level) {
        final var converter = this.errorConverterFactory.getConverter(exceptionClass);
        final var errorResponse = converter.toErrorResponse(ex);

        if(level == ERROR) {
            log.error("m={}, ex={}", method, ex);
        } else {
            log.error("m={}, ex={}", method, status);
        }

        return new ResponseEntity<>(status);
    }

    @ExceptionHandler(MusicaNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleMusicaNotFoundException
            (final MusicaNotFoundException ex) {

        final var converter = this.errorConverterFactory.getConverter(MusicaNotFoundException.class);
        final var errorResponse = converter.toErrorResponse(ex);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(GeneroNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleGeneroNotFoundException
            (final GeneroNotFoundException ex) {

        final var converter = this.errorConverterFactory.getConverter(GeneroNotFoundException.class);
        final var errorResponse = converter.toErrorResponse(ex);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(AutorNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleAutorNotFoundException
            (final AutorNotFoundException ex) {

        final var converter = this.errorConverterFactory.getConverter(AutorNotFoundException.class);
        final var errorResponse = converter.toErrorResponse(ex);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
