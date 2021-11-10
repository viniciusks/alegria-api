package br.com.autadesouza.alegriaapi.service;

import br.com.autadesouza.alegriaapi.controller.request.LoginRequest;
import br.com.autadesouza.alegriaapi.controller.response.LoginResponse;
import br.com.autadesouza.alegriaapi.repository.model.Usuario;

import java.io.IOException;
import java.util.List;

public interface AuthService {

    Usuario register(Usuario usuario);

    LoginResponse login(LoginRequest loginRequest);

    List<String> getMunicipiosByUf(String uf) throws IOException;
}
