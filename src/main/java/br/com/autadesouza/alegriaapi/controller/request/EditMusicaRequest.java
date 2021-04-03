package br.com.autadesouza.alegriaapi.controller.request;

import br.com.autadesouza.alegriaapi.repository.model.Musica;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import br.com.autadesouza.alegriaapi.validation.annotation.Values;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class EditMusicaRequest {

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String titulo;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    @Size(message = "{invalid_size_max}", max = 7, groups = Values.class)
    private String tonalidade;

    private List<AutorRequest> autores;

    private List<GeneroRequest> generos;

    private Set<LetraRequest> letras;

    public Musica toDomain() {
        var autores = this.autores.stream()
                .map(AutorRequest::toDomainWithId)
                .collect(Collectors.toList());

        var generos = this.generos.stream()
                .map(GeneroRequest::toDomainWithId)
                .collect(Collectors.toList());

        var letras = this.letras.stream()
                .map(LetraRequest::toDomainWithId)
                .collect(Collectors.toSet());

        return Musica.builder()
                .titulo(this.titulo)
                .tonalidade(this.tonalidade)
                .autores(autores)
                .generos(generos)
                .letras(letras)
                .build();
    }
}
