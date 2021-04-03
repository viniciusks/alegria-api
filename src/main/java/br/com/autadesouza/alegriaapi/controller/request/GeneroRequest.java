package br.com.autadesouza.alegriaapi.controller.request;

import br.com.autadesouza.alegriaapi.repository.model.Genero;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class GeneroRequest {

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private Long id;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String nome;

    public Genero toDomain() {
        return Genero.builder()
                .nome(this.nome)
                .build();
    }

    public Genero toDomainWithId() {
        return Genero.builder()
                .id(this.id)
                .nome(this.nome)
                .build();
    }
}
