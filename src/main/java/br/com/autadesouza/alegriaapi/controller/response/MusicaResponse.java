package br.com.autadesouza.alegriaapi.controller.response;

import br.com.autadesouza.alegriaapi.repository.model.Musica;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class MusicaResponse {

    private Long id;

    private String titulo;

    private String tonalidade;

    public static MusicaResponse fromDomain(Musica musica) {
        return MusicaResponse.builder()
                .id(musica.getId())
                .titulo(musica.getTitulo())
                .tonalidade(musica.getTonalidade())
                .build();
    }

    public static List<MusicaResponse> fromDomain(List<Musica> musicas) {
        return musicas.stream().map(m -> MusicaResponse.builder()
                .id(m.getId())
                .titulo(m.getTitulo())
                .tonalidade(m.getTonalidade())
                .build())
                .collect(Collectors.toList());
    }
}
