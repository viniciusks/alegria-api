package br.com.autadesouza.alegriaapi.service.impl;

import br.com.autadesouza.alegriaapi.repository.MusicaRepository;
import br.com.autadesouza.alegriaapi.repository.entity.Musica;
import br.com.autadesouza.alegriaapi.service.MusicaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MusicaServiceImpl implements MusicaService {

    private final MusicaRepository musicaRepository;

    @Override
    public Musica createMusica(Musica musica) {
        return musicaRepository.save(musica);
    }
}
