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
@Table(name = "autor")
public class Autor {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", length = -1)
    private String nome;

    @Column(name = "email", length = -1)
    private String email;

    @Column(name = "telefone", length = -1)
    private String telefone;

    @Column(name = "pais", length = 3)
    private String pais;

    @Column(name = "estado", length = -1)
    private String estado;

    @Column(name = "cidade", length = -1)
    private String cidade;
}
