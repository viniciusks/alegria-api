package br.com.autadesouza.alegriaapi.controller.response;

import br.com.autadesouza.alegriaapi.repository.model.Autor;
import br.com.autadesouza.alegriaapi.repository.model.Genero;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
public class GeneroResponse {

    private long id;

    private String nome;

    public static GeneroResponse fromDomain(Genero genero) {
        return GeneroResponse.builder()
                .id(genero.getId())
                .nome(genero.getNome())
                .build();
    }
}
