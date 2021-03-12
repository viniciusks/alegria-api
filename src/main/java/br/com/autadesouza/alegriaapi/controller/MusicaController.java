package br.com.autadesouza.alegriaapi.controller;

import br.com.autadesouza.alegriaapi.controller.request.MusicaRequest;
import br.com.autadesouza.alegriaapi.controller.response.MusicaResponse;
import br.com.autadesouza.alegriaapi.controller.validator.MusicaRequestValidator;
import br.com.autadesouza.alegriaapi.service.MusicaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validator;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/musicas")
@AllArgsConstructor
public class MusicaController {

    private final MusicaService musicaService;

    private final MusicaRequestValidator musicaRequestValidator;

    @PostMapping
    public ResponseEntity<MusicaResponse> createMusica(@RequestBody @Validated MusicaRequest musicaRequest) {

        musicaRequestValidator.validate(musicaRequest);

        final var musica = MusicaResponse.fromDomain(musicaService.createMusica(musicaRequest.toDomain()));
        return new ResponseEntity<>(musica, CREATED);
    }
}
