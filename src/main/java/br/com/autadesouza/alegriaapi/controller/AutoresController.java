package br.com.autadesouza.alegriaapi.controller;

import br.com.autadesouza.alegriaapi.controller.request.AutorRequest;
import br.com.autadesouza.alegriaapi.controller.response.AutorResponse;
import br.com.autadesouza.alegriaapi.service.AutoresService;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import br.com.autadesouza.alegriaapi.validation.annotation.Values;
import br.com.autadesouza.alegriaapi.validation.validator.AutoresRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/autores")
@AllArgsConstructor
public class AutoresController {

    private final AutoresService autoresService;

    private final AutoresRequestValidator autoresRequestValidator;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorResponse> createAutor(@RequestBody @Validated({Mandatory.class, Values.class}) AutorRequest autorRequest) {
        autoresRequestValidator.validate(autorRequest);

        final var autor = AutorResponse.fromDomain(autoresService.createAutor(autorRequest.toDomain()));
        return new ResponseEntity<>(autor, CREATED);
    }

    @GetMapping
    public ResponseEntity<AutorResponse> getAutores() {
        List<AutorResponse> autores = AutorResponse.fromDomain(autoresService.getAutores());
        return new ResponseEntity(autores, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> getAutorById(@PathVariable("id") String id) throws Exception {
        AutorResponse autor = AutorResponse.fromDomain(autoresService.getAutorById(id));
        return new ResponseEntity(autor, OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> editAutor(@RequestBody @Validated({Mandatory.class, Values.class})
                                                             AutorRequest autorRequest, @PathVariable("id") String id) throws Exception {
        autoresRequestValidator.validate(autorRequest);

        final var autor = AutorResponse.fromDomain(autoresService
                .editAutor(autorRequest.toDomain(), id));
        return new ResponseEntity<>(autor, OK);
    }
}
