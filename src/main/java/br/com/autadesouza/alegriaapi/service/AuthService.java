package br.com.autadesouza.alegriaapi.service;

import br.com.autadesouza.alegriaapi.repository.model.User;

public interface AuthService {

    public User register(User user);
}
