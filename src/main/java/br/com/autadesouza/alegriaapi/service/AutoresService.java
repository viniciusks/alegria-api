package br.com.autadesouza.alegriaapi.service;


import br.com.autadesouza.alegriaapi.repository.model.Autor;

import java.util.List;

public interface AutoresService {

    Autor createAutor(final Autor autor);

    List<Autor> getAutores();

    Autor getAutorById(String id);

    Autor editAutor(final Autor novoAutor, final String idAutor) throws Exception;
}
