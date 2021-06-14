package br.com.autadesouza.alegriaapi.controller.response;

import br.com.autadesouza.alegriaapi.repository.model.Genero;
import br.com.autadesouza.alegriaapi.repository.model.Musica;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
public class GeneroResponse {

    private String id;

    private String nome;

    public static GeneroResponse fromDomain(Genero genero) {
        return GeneroResponse.builder()
                .id(genero.getId())
                .nome(genero.getNome())
                .build();
    }

    public static List<GeneroResponse> fromDomain(List<Genero> generos) {
        return generos.stream().map(genero -> fromDomain(genero))
                .collect(Collectors.toList());
    }
}
