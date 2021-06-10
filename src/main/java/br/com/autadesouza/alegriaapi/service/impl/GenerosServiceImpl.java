package br.com.autadesouza.alegriaapi.service.impl;

import br.com.autadesouza.alegriaapi.repository.GenerosRepository;
import br.com.autadesouza.alegriaapi.repository.model.Genero;
import br.com.autadesouza.alegriaapi.service.GenerosService;
import br.com.autadesouza.alegriaapi.validation.exception.GeneroNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class GenerosServiceImpl implements GenerosService {

    private final GenerosRepository generosRepository;

    @Override
    public List<Genero> getGeneros() {
        return generosRepository.findAll();
    }

    @Override
    public Genero getGeneroById(String id) {
        Optional<Genero> optGenero = generosRepository.findById(id);
        return optGenero.orElseThrow(() -> {
            log.info("m=getGeneros msg=Genero not found, generoId={}", id);
            throw new GeneroNotFoundException("Genero not found.");
        });
    }


}
