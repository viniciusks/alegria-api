package br.com.autadesouza.alegriaapi.controller.response;

import br.com.autadesouza.alegriaapi.repository.model.Musica;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class MusicaResponse {

    private Long id;

    private String titulo;

    private String tonalidade;

    private List<AutorResponse> autores;

    private List<GeneroResponse> generos;

    private Set<LetraResponse> letras;

    public static MusicaResponse fromDomain(Musica musica) {

        var autoresMusica = Objects.isNull(musica.getAutores()) ? null
                : musica.getAutores().stream().map(AutorResponse::fromDomain).collect(Collectors.toList());

        var generos = Objects.isNull(musica.getGeneros()) ? null
                : musica.getGeneros().stream().map(GeneroResponse::fromDomain).collect(Collectors.toList());

        var letras = Objects.isNull(musica.getLetras()) ? null
                : musica.getLetras().stream().map(LetraResponse::fromDomain).collect(Collectors.toSet());

        return MusicaResponse.builder()
                .id(musica.getId())
                .titulo(musica.getTitulo())
                .tonalidade(musica.getTonalidade())
                .autores(autoresMusica)
                .generos(generos)
                .letras(letras)
                .build();
    }

    public static List<MusicaResponse> fromDomain(List<Musica> musicas) {
        return musicas.stream().map(musica -> fromDomain(musica))
                .collect(Collectors.toList());
    }
}
