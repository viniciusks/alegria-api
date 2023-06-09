package br.com.autadesouza.alegriaapi.repository;

import br.com.autadesouza.alegriaapi.repository.model.Genero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerosRepository extends MongoRepository<Genero, String> {
}
