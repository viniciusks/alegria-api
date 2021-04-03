package br.com.autadesouza.alegriaapi.service.impl;

import br.com.autadesouza.alegriaapi.repository.GenerosRepository;
import br.com.autadesouza.alegriaapi.repository.model.Genero;
import br.com.autadesouza.alegriaapi.repository.model.Musica;
import br.com.autadesouza.alegriaapi.service.GenerosService;
import br.com.autadesouza.alegriaapi.validation.exception.GeneroNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GenerosServiceImpl implements GenerosService {

    private final GenerosRepository generosRepository;

    @Override
    public Genero getGeneroById(Long id) throws Exception {
        Optional<Genero> optGenero = generosRepository.findById(id);
        return optGenero.orElseThrow(() -> new GeneroNotFoundException("Genero not found."));
    }
}
