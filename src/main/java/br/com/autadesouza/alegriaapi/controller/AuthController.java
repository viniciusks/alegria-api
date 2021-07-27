package br.com.autadesouza.alegriaapi.controller;

import br.com.autadesouza.alegriaapi.repository.UserRepository;
import br.com.autadesouza.alegriaapi.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/oauth")
@AllArgsConstructor
public class AuthController {

    private UserRepository userRepository;

    @GetMapping("/login")
    public ResponseEntity<User> login() throws Exception {
        return new ResponseEntity(new User(), OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register() throws Exception {
        User user = new User();
        user.setEmail("vini_admin@gmail.com");
        user.setPassword("viniadmin");

        userRepository.save(user);
        return new ResponseEntity(user, OK);
    }
}
