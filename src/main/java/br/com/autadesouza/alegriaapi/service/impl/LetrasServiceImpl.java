package br.com.autadesouza.alegriaapi.service.impl;

import br.com.autadesouza.alegriaapi.repository.LetrasRepository;
import br.com.autadesouza.alegriaapi.repository.model.Letra;
import br.com.autadesouza.alegriaapi.service.LetrasService;
import br.com.autadesouza.alegriaapi.validation.exception.LetraNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class LetrasServiceImpl implements LetrasService {

    private LetrasRepository letrasRepository;

    @Override
    public Letra createLetra(Letra letra) {
        return letrasRepository.save(letra);
    }

    @Override
    public List<Letra> createLetra(List<Letra> letras) {
        return letrasRepository.saveAll(letras);
    }

    @Override
    public Letra getLetraById(String id) {
        Optional<Letra> optGenero = letrasRepository.findById(id);
        return optGenero.orElseThrow(() -> {
            log.info("m=getGeneros msg=Letra not found, letraId={}", id);
            return new LetraNotFoundException("Letra not found.");
        });
    }

    @Override
    public Letra editLetra(Letra letraAtualizada) {
        Letra letraAtual = this.getLetraById(letraAtualizada.getId());
        letraAtual.setOrdem(letraAtualizada.getOrdem());
        letraAtual.setTexto(letraAtualizada.getTexto());
        letraAtual.setTipo(letraAtualizada.getTipo());

        return letrasRepository.save(letraAtual);
    }
}
