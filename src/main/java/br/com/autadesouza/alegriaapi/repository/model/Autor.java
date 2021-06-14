package br.com.autadesouza.alegriaapi.repository.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document
public class Autor {

    @Id
    private String id;

    private String nome;

    private String email;

    private String telefone;

    private String pais;

    private String estado;

    private String cidade;
}
