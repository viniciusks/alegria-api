package br.com.autadesouza.alegriaapi.service.impl;

import br.com.autadesouza.alegriaapi.repository.MusicasRepository;
import br.com.autadesouza.alegriaapi.repository.model.Autor;
import br.com.autadesouza.alegriaapi.repository.model.Genero;
import br.com.autadesouza.alegriaapi.repository.model.Letra;
import br.com.autadesouza.alegriaapi.repository.model.Musica;
import br.com.autadesouza.alegriaapi.service.AutoresService;
import br.com.autadesouza.alegriaapi.service.GenerosService;
import br.com.autadesouza.alegriaapi.service.LetrasService;
import br.com.autadesouza.alegriaapi.service.MusicasService;
import br.com.autadesouza.alegriaapi.validation.exception.MusicaNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class MusicasServiceImpl implements MusicasService {

    private final MusicasRepository musicasRepository;

    private final GenerosService generosService;

    private final AutoresService autoresService;

    private final LetrasService letrasService;

    @Override
    public Musica createMusica(Musica musica) {
        List<Genero> generosMusica = getGeneros(musica);
        List<Autor> autoresMusica = getAutores(musica);
        List<Letra> letrasMusica = createLetras(musica.getLetras());

        musica.setGeneros(generosMusica);
        musica.setAutores(autoresMusica);
        musica.setLetras(letrasMusica);

        var novaMusica = musicasRepository.save(musica);
        return novaMusica;
    }

    private List<Letra> createLetras(List<Letra> letras) {
        return letrasService.createLetra(letras);
    }

    private List<Autor> getAutores(Musica musica) {
        List<Autor> autoresMusica = musica.getAutores().stream()
                .map(autor -> autoresService.getAutorById(autor.getId()))
                .collect(Collectors.toList());
        return autoresMusica;
    }

    private List<Genero> getGeneros(Musica musica) {
        List<Genero> generosMusica = musica.getGeneros().stream()
                .map(genero -> generosService.getGeneroById(genero.getId()))
                .collect(Collectors.toList());
        return generosMusica;
    }

    @Override
    public List<Musica> getMusicas() {
        return musicasRepository.findAll();
    }

    @Override
    public Musica getMusicaById(String id) {
        Optional<Musica> optMusica = musicasRepository.findById(id);
        return optMusica.orElseThrow(() -> {
            log.info("m=getMusicaById msg=Musica not found, musicaId={}", id);
            return new MusicaNotFoundException("Musica not found.");
        });
    }

    @Override
    public Musica editMusica(Musica musicaAtualizada, String idMusica) {
        Musica musica = this.getMusicaById(idMusica);
        musica.setTitulo(musicaAtualizada.getTitulo());
        musica.setTonalidade(musicaAtualizada.getTonalidade());
        musica.setAutores(musicaAtualizada.getAutores());
        musica.setGeneros(musicaAtualizada.getGeneros());
        musica.setLetras(createLetras(musicaAtualizada.getLetras()));

        return musicasRepository.save(musica);
    }

    @Override
    public void deleteMusica(String id) {
        Musica musica = this.getMusicaById(id);
        musicasRepository.delete(musica);
    }
}
