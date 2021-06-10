package br.com.autadesouza.alegriaapi.service;


import br.com.autadesouza.alegriaapi.repository.model.Genero;

import java.util.List;

public interface GenerosService {

    List<Genero> getGeneros();

    Genero getGeneroById(String id);
}
