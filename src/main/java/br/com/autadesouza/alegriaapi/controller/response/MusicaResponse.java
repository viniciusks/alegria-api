package br.com.autadesouza.alegriaapi.controller.response;

import br.com.autadesouza.alegriaapi.repository.entity.Musica;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
}
