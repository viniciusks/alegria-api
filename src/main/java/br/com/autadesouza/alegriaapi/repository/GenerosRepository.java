package br.com.autadesouza.alegriaapi.repository;

import br.com.autadesouza.alegriaapi.repository.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerosRepository extends JpaRepository<Genero, Long> {
}
