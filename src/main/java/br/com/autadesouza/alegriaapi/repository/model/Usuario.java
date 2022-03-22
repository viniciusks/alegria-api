package br.com.autadesouza.alegriaapi.repository.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document
public class Usuario {

    @Id
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

    @DBRef
    private List<Role> roles;

    private String artisticFormation;

    private String professionalArt;

    private String englishLevel;

    private String spanishLevel;

    private String otherLanguages;

    private String visualArts;

    private Boolean isWorker;

    private Boolean isPlayer;

    private Boolean isTheater;

    private Boolean isLiterature;

    private Boolean isDancer;

    private Boolean isEFASCoordinator;

    private Boolean isCONCAFRASCoordinator;

    private Boolean isActive;

    private ArrayList<String> instruments;

    private String image;

    private String coordinator;
}
