package br.com.autadesouza.alegriaapi.controller;

import br.com.autadesouza.alegriaapi.controller.request.MusicaRequest;
import br.com.autadesouza.alegriaapi.controller.response.MusicaResponse;
import br.com.autadesouza.alegriaapi.service.MusicaService;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import br.com.autadesouza.alegriaapi.validation.validator.MusicaRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/musicas")
@AllArgsConstructor
public class MusicaController {

    private final MusicaService musicaService;

    private final MusicaRequestValidator musicaRequestValidator;

    @PostMapping
    public ResponseEntity<MusicaResponse> createMusica(@RequestBody @Validated({Mandatory.class}) MusicaRequest musicaRequest) {
        musicaRequestValidator.validate(musicaRequest);

        final var musica = MusicaResponse.fromDomain(musicaService.createMusica(musicaRequest.toDomain()));
        return new ResponseEntity<>(musica, CREATED);
    }

    @GetMapping
    public ResponseEntity<MusicaResponse> getMusicas() {
        List<MusicaResponse> musicas = MusicaResponse.fromDomain(musicaService.getMusicas());
        return new ResponseEntity(musicas, CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicaResponse> getMusicaById(@PathParam("id") Long id) throws Exception {
        MusicaResponse musica = MusicaResponse.fromDomain(musicaService.getMusicaById(id));
        return new ResponseEntity(musica, CREATED);
    }
}
