package br.com.autadesouza.alegriaapi.repository;

import br.com.autadesouza.alegriaapi.repository.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepoistory extends MongoRepository<Role, String> {

    Role findByNomeRole(String nomeRole);

}
