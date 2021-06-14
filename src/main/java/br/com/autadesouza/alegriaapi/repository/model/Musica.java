package br.com.autadesouza.alegriaapi.repository.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document
public class Musica {

    @Id
    private String id;

    private String titulo;

    private String tonalidade;

    @DBRef
    private List<Autor> autores;

    @DBRef
    private List<Genero> generos;

    @DBRef
    private List<Letra> letras;
}
