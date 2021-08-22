package br.com.autadesouza.alegriaapi.controller.response;

import br.com.autadesouza.alegriaapi.repository.model.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
public class UserResponse {

    private String id;

    private String name;

    private String lastname;

    private String fullname;

    private LocalDateTime birthday;

    private String phoneNumber;

    private String email;

    private String password;

    private String country;

    private String state;

    private String role;

    private String city;

    private String image;

    public static UserResponse fromDomain(Usuario usuario) {
        return UserResponse.builder()
                .id(usuario.getId())
                .name(usuario.getName())
                .lastname(usuario.getLastname())
                .fullname(usuario.getFullname())
                .birthday(usuario.getBirthday())
                .phoneNumber(usuario.getPhoneNumber())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .country(usuario.getCountry())
                .state(usuario.getState())
                .role(usuario.getRoles().get(0).getNomeRole())
                .city(usuario.getCity())
                .image(usuario.getImage())
                .build();
    }
}
