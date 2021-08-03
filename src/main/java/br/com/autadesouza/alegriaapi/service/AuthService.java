package br.com.autadesouza.alegriaapi.service;

import br.com.autadesouza.alegriaapi.repository.model.Usuario;

public interface AuthService {

    Usuario register(Usuario usuario);
}
