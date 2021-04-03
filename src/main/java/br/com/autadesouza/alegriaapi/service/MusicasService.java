package br.com.autadesouza.alegriaapi.service;


import br.com.autadesouza.alegriaapi.repository.model.Musica;

import java.util.List;

public interface MusicasService {

    Musica createMusica(final Musica musica);

    List<Musica> getMusicas();

    Musica getMusicaById(Long id) throws Exception;

    Musica editMusica(final Musica novaMusica, final Long idMusica) throws Exception;
}
