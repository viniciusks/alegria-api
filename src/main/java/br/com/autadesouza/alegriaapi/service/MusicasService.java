package br.com.autadesouza.alegriaapi.service;


import br.com.autadesouza.alegriaapi.repository.model.Musica;

import java.util.List;

public interface MusicasService {

    Musica createMusica(final Musica musica);

    List<Musica> getMusicas();

    Musica getMusicaById(String id);

    Musica editMusica(final Musica musicaAtualizada, final String idMusica);

    void deleteMusica(String id);
}
