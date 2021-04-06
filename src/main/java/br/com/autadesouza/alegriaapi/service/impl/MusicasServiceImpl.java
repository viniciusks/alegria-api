package br.com.autadesouza.alegriaapi.service.impl;

import br.com.autadesouza.alegriaapi.repository.MusicasRepository;
import br.com.autadesouza.alegriaapi.repository.model.Autor;
import br.com.autadesouza.alegriaapi.repository.model.Genero;
import br.com.autadesouza.alegriaapi.repository.model.Musica;
import br.com.autadesouza.alegriaapi.service.AutoresService;
import br.com.autadesouza.alegriaapi.service.GenerosService;
import br.com.autadesouza.alegriaapi.service.MusicasService;
import br.com.autadesouza.alegriaapi.validation.exception.AutorNotFoundException;
import br.com.autadesouza.alegriaapi.validation.exception.GeneroNotFoundException;
import br.com.autadesouza.alegriaapi.validation.exception.MusicaNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MusicasServiceImpl implements MusicasService {

    private final MusicasRepository musicasRepository;

    private final GenerosService generosService;

    private final AutoresService autoresService;

    @Override
    @Transactional
    public Musica createMusica(Musica musica) {
        List<Genero> generosMusica = getGeneros(musica);
        List<Autor> autoresMusica = getAutores(musica);

        musica.setGeneros(generosMusica);
        musica.setAutores(autoresMusica);

        var novaMusica = musicasRepository.saveAndFlush(musica);
        return novaMusica;
    }

    private List<Autor> getAutores(Musica musica) {
        List<Autor> autoresMusica = musica.getAutores().stream()
                .map(autor -> {
                    try {
                        return autoresService.getAutorById(autor.getId());
                    } catch (Exception e) {
                        throw new AutorNotFoundException("Autor not found.");
                    }
                })
                .collect(Collectors.toList());
        return autoresMusica;
    }

    private List<Genero> getGeneros(Musica musica) {
        List<Genero> generosMusica = musica.getGeneros().stream()
                .map(genero -> {
                    try {
                        return generosService.getGeneroById(genero.getId());
                    } catch (Exception e) {
                        throw new GeneroNotFoundException("Genero not found.");
                    }
                })
                .collect(Collectors.toList());
        return generosMusica;
    }

    @Override
    public List<Musica> getMusicas() {
        return musicasRepository.findAll();
    }

    @Override
    public Musica getMusicaById(Long id) throws Exception {
        Optional<Musica> optMusica = musicasRepository.findById(id);
        return optMusica.orElseThrow(() -> new MusicaNotFoundException("Musica not found."));
    }

    @Override
    @Transactional
    public Musica editMusica(Musica novaMusica, Long idMusica) throws Exception {
        Optional<Musica> optMusica = musicasRepository.findById(idMusica);
        if(optMusica.isPresent()) {
            Musica musica = optMusica.get();
            musica.setTitulo(novaMusica.getTitulo());
            musica.setTonalidade(novaMusica.getTonalidade());
            musica.setAutores(novaMusica.getAutores());
            musica.setGeneros(novaMusica.getGeneros());
            musica.setLetras(novaMusica.getLetras());

            return this.createMusica(musica);
        } else {
            throw new MusicaNotFoundException("Musica not found.");
        }
    }
}
