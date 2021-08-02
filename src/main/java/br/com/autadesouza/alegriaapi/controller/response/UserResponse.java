package br.com.autadesouza.alegriaapi.controller.response;

import br.com.autadesouza.alegriaapi.repository.model.Autor;
import br.com.autadesouza.alegriaapi.repository.model.User;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private String city;

    private String role;

    private String image;

    public static UserResponse fromDomain(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .fullname(user.getFullname())
                .birthday(user.getBirthday())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .password(user.getPassword())
                .country(user.getCountry())
                .state(user.getState())
                .city(user.getCity())
                .role(user.getRole())
                .image(user.getImage())
                .build();
    }
}
