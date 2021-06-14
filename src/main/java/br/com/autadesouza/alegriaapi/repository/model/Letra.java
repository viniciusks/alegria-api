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
public class Letra {

    @Id
    private String id;

    private Integer ordem;

    private String tipo;

    private String texto;
}
