package br.com.autadesouza.alegriaapi.controller.request;

import br.com.autadesouza.alegriaapi.repository.model.Musica;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class MusicaRequest {

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String titulo;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String tonalidade;

    public Musica toDomain() {
        return Musica.builder()
                .titulo(this.titulo)
                .tonalidade(this.tonalidade)
                .build();
    }
}
