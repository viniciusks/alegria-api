package br.com.autadesouza.alegriaapi.service;


import br.com.autadesouza.alegriaapi.repository.model.Genero;

public interface GenerosService {

    Genero getGeneroById(Long id) throws Exception;
}
