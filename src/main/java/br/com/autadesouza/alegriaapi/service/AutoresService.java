package br.com.autadesouza.alegriaapi.service;


import br.com.autadesouza.alegriaapi.repository.model.Autor;
import br.com.autadesouza.alegriaapi.repository.model.Musica;

import java.util.List;

public interface AutoresService {

    Autor createAutor(final Autor autor);

    List<Autor> getAutores();

    Autor getAutorById(Long id) throws Exception;

    Autor editAutor(final Autor novoAutor, final Long idAutor) throws Exception;
}
