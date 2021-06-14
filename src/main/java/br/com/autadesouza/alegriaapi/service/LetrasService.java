package br.com.autadesouza.alegriaapi.service;

import br.com.autadesouza.alegriaapi.repository.model.Letra;

import java.util.List;

public interface LetrasService {

    Letra createLetra(final Letra letra);

    List<Letra> createLetra(List<Letra> letras);

    Letra editLetra(final Letra letraAtualizada);

    Letra getLetraById(String id);
}
