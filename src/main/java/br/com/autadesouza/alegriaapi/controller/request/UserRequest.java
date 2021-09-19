package br.com.autadesouza.alegriaapi.controller.request;

import br.com.autadesouza.alegriaapi.repository.model.Role;
import br.com.autadesouza.alegriaapi.repository.model.Usuario;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private String whatsapp;

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

    private String artisticFormation;

    private String professionalArt;

    private String englishLevel;

    private String spanishLevel;

    private String otherLanguages;

    private String visualArts;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private Boolean isWorker;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private Boolean isPlayer;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private Boolean isTheater;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private Boolean isLiterature;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private Boolean isDancer;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private Boolean isEFASCoordinator;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private Boolean isCONCAFRASCoordinator;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private Boolean isActive;

    private ArrayList<String> instruments;

    private String image;

    private String coordinator;

    public Usuario toDomain(Role role) {

        return Usuario.builder()
                .name(this.name)
                .lastname(this.lastname)
                .fullname(this.fullname)
                .birthday(this.birthday)
                .phoneNumber(this.whatsapp)
                .email(this.email)
                .password(this.password)
                .country(this.country)
                .state(this.state)
                .city(this.city)
                .roles(Arrays.asList(role))
                .artisticFormation(this.artisticFormation)
                .professionalArt(this.professionalArt)
                .englishLevel(this.englishLevel)
                .spanishLevel(this.spanishLevel)
                .otherLanguages(this.otherLanguages)
                .visualArts(this.visualArts)
                .isWorker(this.isWorker)
                .isPlayer(this.isPlayer)
                .isTheater(this.isTheater)
                .isLiterature(this.isLiterature)
                .isDancer(this.isDancer)
                .isEFASCoordinator(this.isEFASCoordinator)
                .isCONCAFRASCoordinator(this.isCONCAFRASCoordinator)
                .isActive(this.isActive)
                .instruments(this.instruments)
                .image(this.image)
                .coordinator(this.coordinator)
                .build();
    }
}
