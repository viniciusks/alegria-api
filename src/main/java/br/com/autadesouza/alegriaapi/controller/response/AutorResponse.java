package br.com.autadesouza.alegriaapi.controller.response;

import br.com.autadesouza.alegriaapi.repository.model.Autor;
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
public class AutorResponse {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String pais;

    private String estado;

    private String cidade;

    public static AutorResponse fromDomain(Autor autor) {
        return AutorResponse.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .email(autor.getEmail())
                .telefone(autor.getTelefone())
                .pais(autor.getPais())
                .estado(autor.getEstado())
                .cidade(autor.getCidade())
                .build();
    }

    public static List<AutorResponse> fromDomain(List<Autor> autores) {
        return autores.stream().map(autor -> AutorResponse.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .email(autor.getEmail())
                .telefone(autor.getTelefone())
                .pais(autor.getPais())
                .estado(autor.getEstado())
                .cidade(autor.getCidade())
                .build())
                .collect(Collectors.toList());
    }
}
