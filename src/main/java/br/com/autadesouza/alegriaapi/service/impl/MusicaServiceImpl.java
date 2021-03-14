package br.com.autadesouza.alegriaapi.service.impl;

import br.com.autadesouza.alegriaapi.repository.MusicaRepository;
import br.com.autadesouza.alegriaapi.repository.model.Musica;
import br.com.autadesouza.alegriaapi.service.MusicaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MusicaServiceImpl implements MusicaService {

    private final MusicaRepository musicaRepository;

    @Override
    @Transactional
    public Musica createMusica(Musica musica) {
        return musicaRepository.save(musica);
    }

    @Override
    public List<Musica> getMusicas() {
        return musicaRepository.findAll();
    }

    @Override
    public Musica getMusicaById(Long id) throws Exception {
        Optional<Musica> optMusica = musicaRepository.findById(id);
        return optMusica.orElseThrow(() -> new Exception("Musica not found."));
    }
}
