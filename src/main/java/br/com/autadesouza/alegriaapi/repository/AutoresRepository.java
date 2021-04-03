package br.com.autadesouza.alegriaapi.repository;

import br.com.autadesouza.alegriaapi.repository.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoresRepository extends JpaRepository<Autor, Long> {
}
