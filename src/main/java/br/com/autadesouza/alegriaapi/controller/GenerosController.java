package br.com.autadesouza.alegriaapi.controller;

import br.com.autadesouza.alegriaapi.controller.response.GeneroResponse;
import br.com.autadesouza.alegriaapi.service.GenerosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/generos")
@AllArgsConstructor
public class GenerosController {

    private final GenerosService generosService;

    @GetMapping
    public ResponseEntity<GeneroResponse> getGeneros() {
        List<GeneroResponse> generos = GeneroResponse.fromDomain(generosService.getGeneros());
        return new ResponseEntity(generos, OK);
    }
}
