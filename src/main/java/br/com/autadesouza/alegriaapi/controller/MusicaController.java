package br.com.autadesouza.alegriaapi.controller;

import br.com.autadesouza.alegriaapi.controller.request.MusicaRequest;
import br.com.autadesouza.alegriaapi.controller.response.MusicaResponse;
import br.com.autadesouza.alegriaapi.service.MusicaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/musica")
@AllArgsConstructor
public class MusicaController {

    private final MusicaService musicaService;

    @PostMapping
    public MusicaResponse getMusicas(@RequestBody MusicaRequest musicaRequest) {
        return MusicaResponse.fromDomain(musicaService.createMusica(musicaRequest.toDomain()));
    }
}
