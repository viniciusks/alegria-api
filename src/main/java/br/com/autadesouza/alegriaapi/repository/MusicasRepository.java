package br.com.autadesouza.alegriaapi.repository;

import br.com.autadesouza.alegriaapi.repository.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicasRepository extends JpaRepository<Musica, Long> {
}
