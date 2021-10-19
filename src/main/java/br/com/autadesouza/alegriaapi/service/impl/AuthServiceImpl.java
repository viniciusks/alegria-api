package br.com.autadesouza.alegriaapi.service.impl;

import br.com.autadesouza.alegriaapi.repository.UserRepository;
import br.com.autadesouza.alegriaapi.repository.model.Usuario;
import br.com.autadesouza.alegriaapi.service.AuthService;
import br.com.autadesouza.alegriaapi.service.client.IbgeClient;
import br.com.autadesouza.alegriaapi.service.client.response.IbgeResponse;
import br.com.autadesouza.alegriaapi.validation.exception.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    private IbgeClient ibgeClient;

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

    @Override
    public List<String> getMunicipiosByUf(String uf) throws IOException {
        var response = ibgeClient.getMunicipiosByUf(uf);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        var ibgeResponseArray = mapper.readValue(response, IbgeResponse[].class);
        List<String> municipios = new ArrayList<>();

        for(IbgeResponse distrito : ibgeResponseArray) {
            municipios.add(distrito.getNome());
        }

        Collections.sort(municipios);

        return municipios;
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
