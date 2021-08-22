package br.com.autadesouza.alegriaapi.controller.request;

import br.com.autadesouza.alegriaapi.repository.RoleRepoistory;
import br.com.autadesouza.alegriaapi.repository.model.Role;
import br.com.autadesouza.alegriaapi.repository.model.Usuario;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String name;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String lastname;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String fullname;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private LocalDateTime birthday;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String phoneNumber;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String email;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String password;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String country;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String state;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String city;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String role;

    private String image;

    public Usuario toDomain(Role role) {

        return Usuario.builder()
                .name(this.name)
                .lastname(this.lastname)
                .fullname(this.fullname)
                .birthday(this.birthday)
                .phoneNumber(this.phoneNumber)
                .email(this.email)
                .password(this.password)
                .country(this.country)
                .state(this.state)
                .city(this.city)
                .roles(Arrays.asList(role))
                .image(this.image)
                .build();
    }
}
