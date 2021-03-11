package br.com.autadesouza.alegriaapi.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "musica")
@Getter
@Setter
@Builder
@SequenceGenerator(name = "SQ_MUSICA_ID", sequenceName = "SQ_MUSICA_ID", allocationSize = 1)
public class Musica {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SQ_MUSICA_ID")
    @Column
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "tonalidade", length = 7)
    private String tonalidade;
}
