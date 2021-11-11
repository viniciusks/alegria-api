package br.com.autadesouza.alegriaapi.controller;

import br.com.autadesouza.alegriaapi.config.ConfigUserDetailsService;
import br.com.autadesouza.alegriaapi.controller.request.LoginRequest;
import br.com.autadesouza.alegriaapi.controller.request.UserRequest;
import br.com.autadesouza.alegriaapi.controller.response.EstadosResponse;
import br.com.autadesouza.alegriaapi.controller.response.LoginResponse;
import br.com.autadesouza.alegriaapi.controller.response.UserResponse;
import br.com.autadesouza.alegriaapi.repository.RoleRepoistory;
import br.com.autadesouza.alegriaapi.repository.model.Estados;
import br.com.autadesouza.alegriaapi.repository.model.Role;
import br.com.autadesouza.alegriaapi.repository.model.Usuario;
import br.com.autadesouza.alegriaapi.service.AuthService;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import br.com.autadesouza.alegriaapi.validation.annotation.Values;
import br.com.autadesouza.alegriaapi.validation.validator.UserRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/oauth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    private UserRequestValidator userRequestValidator;

    private RoleRepoistory roleRepoistory;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated({Mandatory.class, Values.class}) LoginRequest loginRequest) throws Exception {
        final var response = authService.login(loginRequest);
        return new ResponseEntity(response, OK);
    }

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> register(@RequestBody @Validated({Mandatory.class, Values.class}) UserRequest userRequest) throws Exception {

        userRequestValidator.validate(userRequest);

        Role role = roleRepoistory.findByNomeRole(userRequest.getRole());

        final var user = UserResponse.fromDomain(authService.register(userRequest.toDomain(role)));
        return new ResponseEntity<>(user, CREATED);
    }

    @GetMapping(value = "/ufs", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadosResponse> getUFs() {
        ArrayList<String> estados = new ArrayList<>();
        for (Estados estado : Estados.values()) {
            estados.add(estado.toString());
        }
        return new ResponseEntity<>(new EstadosResponse(estados), OK);
    }

    @GetMapping(value = "/ufs/{UF}/municipios", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getMunicipiosByUf(@PathVariable("UF") String uf) throws IOException {

        final var municipios = authService.getMunicipiosByUf(uf);

        return new ResponseEntity<>(municipios, OK);
    }
}
