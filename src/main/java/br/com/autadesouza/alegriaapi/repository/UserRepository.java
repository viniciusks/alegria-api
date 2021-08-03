package br.com.autadesouza.alegriaapi.repository;

import br.com.autadesouza.alegriaapi.repository.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Usuario, String> {

    Usuario findByEmail(String email);
}
