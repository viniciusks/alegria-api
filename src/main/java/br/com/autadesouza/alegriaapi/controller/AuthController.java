package br.com.autadesouza.alegriaapi.controller;

import br.com.autadesouza.alegriaapi.controller.request.UserRequest;
import br.com.autadesouza.alegriaapi.controller.response.EstadosResponse;
import br.com.autadesouza.alegriaapi.controller.response.UserResponse;
import br.com.autadesouza.alegriaapi.repository.RoleRepoistory;
import br.com.autadesouza.alegriaapi.repository.model.Estados;
import br.com.autadesouza.alegriaapi.repository.model.Role;
import br.com.autadesouza.alegriaapi.service.AuthService;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import br.com.autadesouza.alegriaapi.validation.annotation.Values;
import br.com.autadesouza.alegriaapi.validation.validator.UserRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/oauth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    private UserRequestValidator userRequestValidator;

    private RoleRepoistory roleRepoistory;

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
