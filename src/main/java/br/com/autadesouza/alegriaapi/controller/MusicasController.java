package br.com.autadesouza.alegriaapi.controller;

import br.com.autadesouza.alegriaapi.controller.request.MusicaRequest;
import br.com.autadesouza.alegriaapi.controller.response.MusicaResponse;
import br.com.autadesouza.alegriaapi.service.MusicasService;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import br.com.autadesouza.alegriaapi.validation.annotation.Values;
import br.com.autadesouza.alegriaapi.validation.validator.MusicasRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/musicas")
@AllArgsConstructor
public class MusicasController {

    private final MusicasService musicasService;

    private final MusicasRequestValidator musicasRequestValidator;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MusicaResponse> createMusica(@RequestBody @Validated({Mandatory.class, Values.class})
                                                                   MusicaRequest musicaRequest) {
        musicasRequestValidator.validate(musicaRequest);

        final var musica = MusicaResponse.fromDomain(musicasService
                .createMusica(musicaRequest.toDomain()));
        return new ResponseEntity<>(musica, CREATED);
    }

    @GetMapping
    public ResponseEntity<MusicaResponse> getMusicas() {
        List<MusicaResponse> musicas = MusicaResponse.fromDomain(musicasService.getMusicas());
        return new ResponseEntity(musicas, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicaResponse> getMusicaById(@PathVariable("id") String id) throws Exception {
        MusicaResponse musica = MusicaResponse.fromDomain(musicasService.getMusicaById(id));
        return new ResponseEntity(musica, OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicaResponse> editMusica(@RequestBody @Validated({Mandatory.class, Values.class})
                                                             MusicaRequest editMusicaRequest, @PathVariable("id") String id) throws Exception {
        musicasRequestValidator.validate(editMusicaRequest);

        final var musica = MusicaResponse.fromDomain(musicasService
                .editMusica(editMusicaRequest.toDomain(), id));
        return new ResponseEntity<>(musica, OK);
    }

    @DeleteMapping("/{id}")
    public void deleteMusica(@PathVariable("id") String id) {
        musicasService.deleteMusica(id);
    }
}
