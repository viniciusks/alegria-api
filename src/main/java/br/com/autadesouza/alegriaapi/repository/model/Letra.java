package br.com.autadesouza.alegriaapi.repository.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "letra")
public class Letra {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "musica_id", nullable = false, insertable = false, updatable = false)
    private Long musicaId;

    @Column(name = "ordem")
    private Integer ordem;

    @Column(name = "tipo", length = -1)
    private String tipo;

    @Column(name = "texto", length = -1)
    private String texto;
}
