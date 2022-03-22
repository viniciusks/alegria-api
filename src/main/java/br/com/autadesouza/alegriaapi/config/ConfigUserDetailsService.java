package br.com.autadesouza.alegriaapi.config;

import br.com.autadesouza.alegriaapi.repository.UserRepository;
import br.com.autadesouza.alegriaapi.repository.model.DetalheUsuario;
import br.com.autadesouza.alegriaapi.repository.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ConfigUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = userRepository.findByEmail(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("User: " + username + " not found.");
        }
        return new DetalheUsuario(Optional.of(usuario));
    }
}
