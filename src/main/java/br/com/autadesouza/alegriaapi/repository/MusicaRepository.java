package br.com.autadesouza.alegriaapi.repository;

import br.com.autadesouza.alegriaapi.repository.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
