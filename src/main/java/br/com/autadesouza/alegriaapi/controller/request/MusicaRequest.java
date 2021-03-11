package br.com.autadesouza.alegriaapi.controller.request;

import br.com.autadesouza.alegriaapi.repository.entity.Musica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MusicaRequest {

    private String titulo;
    private String tonalidade;

    public Musica toDomain() {
        return Musica.builder()
                .titulo(this.titulo)
                .tonalidade(this.tonalidade)
                .build();
    }
}
