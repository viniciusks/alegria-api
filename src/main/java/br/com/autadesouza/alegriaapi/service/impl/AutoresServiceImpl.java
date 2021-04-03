package br.com.autadesouza.alegriaapi.service.impl;

import br.com.autadesouza.alegriaapi.repository.AutoresRepository;
import br.com.autadesouza.alegriaapi.repository.model.Autor;
import br.com.autadesouza.alegriaapi.repository.model.Musica;
import br.com.autadesouza.alegriaapi.service.AutoresService;
import br.com.autadesouza.alegriaapi.validation.exception.AutorNotFoundException;
import br.com.autadesouza.alegriaapi.validation.exception.MusicaNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AutoresServiceImpl implements AutoresService {

    private final AutoresRepository autoresRepository;

    @Override
    @Transactional
    public Autor createAutor(Autor autor) {
        return autoresRepository.save(autor);
    }

    @Override
    public List<Autor> getAutores() {
        return autoresRepository.findAll();
    }

    @Override
    public Autor getAutorById(Long id) throws Exception {
        Optional<Autor> optAutor = autoresRepository.findById(id);
        return optAutor.orElseThrow(() -> new AutorNotFoundException("Autor not found."));
    }

    @Override
    public Autor editAutor(Autor novoAutor, Long idAutor) throws Exception {
        Optional<Autor> optAutor = autoresRepository.findById(idAutor);
        if(optAutor.isPresent()) {
            Autor autor = optAutor.get();
            autor.setNome(novoAutor.getNome());
            autor.setEmail(novoAutor.getEmail());
            autor.setTelefone(novoAutor.getTelefone());
            autor.setPais(novoAutor.getPais());
            autor.setEstado(novoAutor.getEstado());
            autor.setCidade(novoAutor.getCidade());
            return autoresRepository.save(autor);
        } else {
            throw new AutorNotFoundException("Musica not found.");
        }
    }
}
