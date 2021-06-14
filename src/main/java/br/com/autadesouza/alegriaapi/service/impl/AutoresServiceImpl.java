package br.com.autadesouza.alegriaapi.service.impl;

import br.com.autadesouza.alegriaapi.repository.AutoresRepository;
import br.com.autadesouza.alegriaapi.repository.model.Autor;
import br.com.autadesouza.alegriaapi.service.AutoresService;
import br.com.autadesouza.alegriaapi.validation.exception.AutorNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class AutoresServiceImpl implements AutoresService {

    @Autowired
    private AutoresRepository autoresRepository;

    @Override
    public Autor createAutor(Autor autor) {
        return autoresRepository.save(autor);
    }

    @Override
    public List<Autor> getAutores() {
        return autoresRepository.findAll();
    }

    @Override
    public Autor getAutorById(String id) {
        Optional<Autor> optAutor = autoresRepository.findById(id);
        return optAutor.orElseThrow(() -> {
            log.info("m=getAutores msg=Autor not found, autorId={}", id);
            throw new AutorNotFoundException("Autor not found.");
        });
    }

    @Override
    public Autor editAutor(Autor novoAutor, String idAutor) throws Exception {
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
