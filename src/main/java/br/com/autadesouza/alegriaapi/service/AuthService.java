package br.com.autadesouza.alegriaapi.service;

import br.com.autadesouza.alegriaapi.repository.model.Usuario;

import java.io.IOException;
import java.util.List;

public interface AuthService {

    Usuario register(Usuario usuario);

    List<String> getMunicipiosByUf(String uf) throws IOException;
}
