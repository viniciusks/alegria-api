package br.com.autadesouza.alegriaapi.service.impl;

import br.com.autadesouza.alegriaapi.repository.UserRepository;
import br.com.autadesouza.alegriaapi.repository.model.Usuario;
import br.com.autadesouza.alegriaapi.service.AuthService;
import br.com.autadesouza.alegriaapi.validation.exception.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    @Override
    public Usuario register(Usuario usuario) {
        Usuario verifyUsuario = userRepository.findByEmail(usuario.getEmail());
        if(verifyUsuario != null) {
            throw new UserAlreadyExistsException("User already exists, please try another email.");
        }

        String encodePass = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setPassword(encodePass);
        return userRepository.save(usuario);
    }

//    @Override
//    public Autor getAutorById(String id) {
//        Optional<Autor> optAutor = autoresRepository.findById(id);
//        return optAutor.orElseThrow(() -> {
//            log.info("m=getAutores msg=Autor not found, autorId={}", id);
//            throw new AutorNotFoundException("Autor not found.");
//        });
//    }
}
