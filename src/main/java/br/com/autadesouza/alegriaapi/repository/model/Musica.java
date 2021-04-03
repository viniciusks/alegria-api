package br.com.autadesouza.alegriaapi.repository.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "musica")
public class Musica {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "titulo", length = -1)
    private String titulo;

    @Column(name = "tonalidade", length = 7)
    private String tonalidade;

    @ManyToMany(cascade = ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "autor_musica",
            joinColumns = {@JoinColumn(name = "musica_id")},
            inverseJoinColumns = {@JoinColumn(name = "autor_id")}
    )
    private List<Autor> autores;

    @OneToMany(cascade = ALL)
    @JoinTable(
            name = "genero_musica",
            joinColumns = {@JoinColumn(name = "musica_id")},
            inverseJoinColumns = {@JoinColumn(name = "genero_id")}
    )
    private List<Genero> generos;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name="musica_id")
    private Set<Letra> letras;

    public void setGeneros(List<Genero> generos) {
        this.generos.clear();
        this.generos.addAll(generos);
    }

    public void setLetras(Set<Letra> letras) {
        this.letras.clear();
        this.letras.addAll(letras);
    }
}
